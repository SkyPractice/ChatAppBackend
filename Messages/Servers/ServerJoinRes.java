package com.app.demo.Messages.Servers;

import com.app.demo.Messages.SocketMessage;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("ServerJoined")
public class ServerJoinRes extends SocketMessage {

    private Integer serverId;
    private String serverName;

    public ServerJoinRes(){

    }

    public ServerJoinRes(Integer serverId) {
        this.serverId = serverId;
    }

    public ServerJoinRes(String sender, Integer serverId, String serverName) {
        super(sender);
        this.serverId = serverId;
        this.serverName = serverName;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }
}
