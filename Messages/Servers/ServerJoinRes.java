package com.app.demo.Messages.Servers;

import com.app.demo.Messages.SocketMessage;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("ServerJoined")
public class ServerJoinRes extends SocketMessage {

    private Integer serverId;

    public ServerJoinRes(){

    }

    public ServerJoinRes(Integer serverId) {
        this.serverId = serverId;
    }

    public ServerJoinRes(String sender, Integer serverId) {
        super(sender);
        this.serverId = serverId;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }
}
