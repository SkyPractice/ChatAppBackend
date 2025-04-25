package com.app.demo.Controllers;

import com.app.demo.Messages.Servers.ChannelMessage;
import com.app.demo.Services.ChannelMsgService;
import com.app.demo.Services.ChannelService;
import com.app.demo.Services.ServerService;
import com.app.demo.Services.ServersJoinedService;
import com.app.demo.Tables.ChannelEntity;
import com.app.demo.Tables.ChannelMessageEntity;
import com.app.demo.Tables.ServerEntity;
import com.app.demo.Tables.ServerJoinedEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// server creation or joining or deletion through websockets only to force Authecation
@RestController
public class ServerController {

    private final ServersJoinedService serversJoinedService;
    private final ServerService serverService;
    private final ChannelService channelService;
    private final ChannelMsgService channelMsgService;

    @Autowired
    public ServerController(ServersJoinedService serversJoinedService, ServerService serverService, ChannelService channelService, ChannelMsgService channelMsgService) {
        this.serversJoinedService = serversJoinedService;
        this.serverService = serverService;
        this.channelService = channelService;
        this.channelMsgService = channelMsgService;
    }

    @GetMapping("/servers/{name}")
    public ServerEntity getServerByName(
            @PathVariable("name") String serverName
    ){
        return serverService.getServerByName(serverName);
    }

    @GetMapping("/servers/joined/{username}")
    public List<ServerJoinedEntity> serversJoinedByName(
            @PathVariable("username") String username
    ){
        return serversJoinedService.getServersJoinedByUserName(username);
    }

    @GetMapping("/servers/channels/{id}")
    public List<ChannelEntity> getChannelsByServerId(
            @PathVariable("id") Integer id
    ){
        return channelService.getChannelsByServerId(id);
    }

    @GetMapping("/servers/channels/msgs/{id}")
    public List<ChannelMessageEntity> getChannelMessageByChannelId(
            @PathVariable("id") Integer id
    ){
        return channelMsgService.getChannelMsgsByChannelId(id);
    }


}
