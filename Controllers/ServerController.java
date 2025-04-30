package com.app.demo.Controllers;

import com.app.demo.Messages.Servers.ChannelMessage;
import com.app.demo.Services.*;
import com.app.demo.Tables.*;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

// server creation or joining or deletion through websockets only to force Authecation
@RestController
public class ServerController {

    private final ServersJoinedService serversJoinedService;
    private final ServerService serverService;
    private final ChannelService channelService;
    private final ChannelMsgService channelMsgService;
    private final UserService userService;

    @Autowired
    public ServerController(ServersJoinedService serversJoinedService, ServerService serverService, ChannelService channelService, ChannelMsgService channelMsgService, UserService userService) {
        this.serversJoinedService = serversJoinedService;
        this.serverService = serverService;
        this.channelService = channelService;
        this.channelMsgService = channelMsgService;
        this.userService = userService;
    }

    @GetMapping("/servers/{name}")
    @Async
    public CompletableFuture<ServerEntity> getServerByName(
            @PathVariable("name") String serverName
    ){
        return CompletableFuture.completedFuture(serverService.getServerByName(serverName));
    }

    @GetMapping("/servers/members/{id}")
    @Async
    public CompletableFuture<List<User>> getServerMembers(
            @PathVariable("id") Integer id
    ){
        List<ServerJoinedEntity> entities = serversJoinedService.getPeopleJoinedByServerId(id);
        Specification<User> userSpecification = ((root, query, criteriaBuilder) ->
        {
            List<Predicate> predicateList = new ArrayList<>();
            for(var joined : entities){
                predicateList.add(
                        criteriaBuilder.equal(root.get("userName"), joined.getUsername())
                );
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        });
        return CompletableFuture.completedFuture(userService.getUserRepos().findAll(userSpecification));
    }

    @GetMapping("/servers/joined/{username}")
    @Async
    public CompletableFuture<List<ServerJoinedEntity>> serversJoinedByName(
            @PathVariable("username") String username
    ){
        return CompletableFuture.completedFuture(serversJoinedService.getServersJoinedByUserName(username));

    }

    @GetMapping("/servers/channels/{id}")
    @Async
    public CompletableFuture<List<ChannelEntity>> getChannelsByServerId(
            @PathVariable("id") Integer id
    ){
        return CompletableFuture.completedFuture(channelService.getChannelsByServerId(id));
    }

    @GetMapping("/servers/channels/msgs/{id}")
    @Async
    public CompletableFuture<List<ChannelMessageEntity>> getChannelMessageByChannelId(
            @PathVariable("id") Integer id
    ){
        List<ChannelMessageEntity> msgs = channelMsgService.getChannelMsgsByChannelId(id).stream().sorted(
                ((o1, o2) -> {
                    if(o1.getId() > o2.getId())
                        return 1;
                    else if(o1.getId() < o2.getId()){
                        return -1;
                    }
                    return 0;
                })
        ).toList();
        if(msgs.size() < 200)
            return CompletableFuture.completedFuture(msgs);
        return CompletableFuture.completedFuture(msgs.subList(msgs.size() - 200 - 1, msgs.size() - 1));
    }


}
