package com.app.demo;

import com.app.demo.Messages.*;
import com.app.demo.Services.FriendRequestService;
import com.app.demo.Services.PrivateMsgService;
import com.app.demo.Services.PublicMsgService;
import com.app.demo.Services.UserService;
import com.app.demo.Tables.FriendRequestEntity;
import com.app.demo.Tables.PrivateMessageEntity;
import com.app.demo.Tables.PublicMessageEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

    private final ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, String> idToName = new ConcurrentHashMap<>();
    private final ObjectMapper mapper = new ObjectMapper(){{
       configure(SerializationFeature.INDENT_OUTPUT, true);
    }};

    private final UserService userService;
    private final PublicMsgService publicMsgService;
    private final PrivateMsgService privateMsgService;
    private final FriendRequestService friendRequestService;

    @Autowired
    public MyWebSocketHandler(UserService userService, PublicMsgService publicMsgService,
                              PrivateMsgService privateMsgService,
                              FriendRequestService friendRequestService) {
        this.userService = userService;
        this.publicMsgService = publicMsgService;
        this.privateMsgService = privateMsgService;
        this.friendRequestService = friendRequestService;
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Client connected: " + session.getId());
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        String payload = message.getPayload();
        System.out.println("Received message from " + session.getId() + ": " + payload);
        SocketMessage msg = mapper.readValue(payload, SocketMessage.class);

        processMessage(session, msg);

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        for(var entry : sessions.entrySet()){
            if(entry.getValue().getId().equals(session.getId())){
                sessions.remove(entry.getKey());
                break;
            }
        }
        idToName.remove(session.getId());
        System.out.println("Client disconnected: " + session.getId() + " with status " + status);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.err.println("Transport error with client " + session.getId() + ": " + exception.getMessage());
    }


    private void processMessage(WebSocketSession socketSession , SocketMessage msg) throws IOException {
        try {
            if(!(msg instanceof AuthenticationMessage) && !idToName.containsKey(socketSession.getId())){
                socketSession.sendMessage(new TextMessage("{\"type\" : \"NoAccess\"}"));
                socketSession.close();
                return;
            }

            if(!(msg instanceof AuthenticationMessage))
                msg.setSender(idToName.get(socketSession.getId()));

            if (msg instanceof AuthenticationMessage message) {
                if(!userService.existsByUsernameAndPassword(message.getUserName(), message.getPassword())){
                    socketSession.sendMessage(new TextMessage("{\"type\" : \"PasswordChange\"}"));
                    socketSession.close();
                    return;
                }
                sessions.put(message.getUserName(), socketSession);
                idToName.put(socketSession.getId(), message.getUserName());
            }
            else if (msg instanceof PublicMessage message){
                if(message.getContent().isEmpty()) return;

                PublicMessageEntity msgEntity = new PublicMessageEntity(message.getSender(), message.getContent());
                publicMsgService.getPublicMsgRepos().save(msgEntity);
                for (WebSocketSession webSocketSession : sessions.values())
                    webSocketSession.sendMessage(new TextMessage(mapper.writeValueAsString(message)));

            }
            else if (msg instanceof PrivateMessage message){
                if(message.getTo().isEmpty()) return;
                if(!friendRequestService.acceptedFriendRequestByName(message.getSender(), message.getTo())){
                    socketSession.sendMessage(new TextMessage("{\"type\" : \"NoDm\"}"));
                    return;
                }

                PrivateMessageEntity privateMessageEntity = new PrivateMessageEntity(
                        message.getSender(), message.getContent(), message.getTo()
                );
                privateMsgService.getPrivateMsgRepos().save(privateMessageEntity);

                WebSocketSession sendToSession = sessions.get(message.getTo());
                if(sendToSession != null)
                    sendToSession.sendMessage(new TextMessage(mapper.writeValueAsString(message)));

            }
            else if (msg instanceof FriendRequestMessage message){

                FriendRequestEntity friendRequestEntity = new FriendRequestEntity(message.getSender(),
                        message.getTo(), false);
                friendRequestService.getFriendRequestRepos().save(friendRequestEntity);
                if(sessions.containsKey(message.getTo()))
                    sessions.get(message.getTo()).sendMessage(new TextMessage(mapper.writeValueAsString(message)));

            }
            else if (msg instanceof FriendRequestResponseMessage message){
                if(message.getAccepted()){
                    FriendRequestEntity friendRequestEntity = new FriendRequestEntity(message.getSender(),
                            message.getTo(), true);
                    friendRequestService.getFriendRequestRepos().save(friendRequestEntity);
                    return;
                }
                friendRequestService.deleteFriendRequestByName(message.getSender(), message.getTo());
                if(sessions.containsKey(message.getTo()))
                    sessions.get(message.getTo()).sendMessage(new TextMessage(mapper.writeValueAsString(message)));
            }
        } catch (JsonProcessingException ex){
            ex.printStackTrace();
        }

    }
}
