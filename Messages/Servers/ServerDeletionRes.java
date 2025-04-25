package com.app.demo.Messages.Servers;

import com.app.demo.Messages.SocketMessage;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("ServerDeleted")
public class ServerDeletionRes extends SocketMessage {

    private Integer serverId;

    public ServerDeletionRes(){

    }

    public ServerDeletionRes(Integer serverId) {
        this.serverId = serverId;
    }

    public ServerDeletionRes(String sender, Integer serverId) {
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
