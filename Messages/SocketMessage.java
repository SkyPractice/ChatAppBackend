package com.app.demo.Messages;

import com.app.demo.Messages.Servers.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
/*
enum MessageType{
    Authentication, Leave, PublicMessage, PrivateMessage
}*/

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes(
        value = {
                @JsonSubTypes.Type(value = AuthenticationMessage.class, name = "Auth"),
                @JsonSubTypes.Type(value = PublicMessage.class, name = "PublicMsg"),
                @JsonSubTypes.Type(value = PrivateMessage.class, name = "PrivateMsg"),
                @JsonSubTypes.Type(value = FriendRequestMessage.class, name = "FriendReq"),
                @JsonSubTypes.Type(value = FriendRequestResponseMessage.class, name = "FriendReqRes"),
                @JsonSubTypes.Type(value = ServerMessage.class, name = "Server"),
                @JsonSubTypes.Type(value = ChannelMessage.class, name = "Channel"),
                @JsonSubTypes.Type(value = PublicChannelMessage.class, name = "ChannelMsg"),
                @JsonSubTypes.Type(value = ServerCreationRes.class, name = "ServerCreated"),
                @JsonSubTypes.Type(value = ServerDeletionRes.class, name = "ServerDeleted"),
                @JsonSubTypes.Type(value = ServerJoinRes.class, name = "ServerJoined")

        }
)
public class SocketMessage {

    @JsonProperty("sender")
    private String sender;

    public SocketMessage(){

    }

    public SocketMessage(String sender){
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
