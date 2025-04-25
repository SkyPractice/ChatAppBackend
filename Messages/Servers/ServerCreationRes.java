package com.app.demo.Messages.Servers;

import com.app.demo.Messages.SocketMessage;
import com.app.demo.Tables.ServerEntity;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("ServerCreated")
public class ServerCreationRes extends SocketMessage {

    private ServerEntity server;

    public ServerCreationRes(ServerEntity server) {
        this.server = server;
    }

    public ServerCreationRes(String sender, ServerEntity server) {
        super(sender);
        this.server = server;
    }

    public ServerEntity getServer() {
        return server;
    }

    public void setServer(ServerEntity server) {
        this.server = server;
    }
}
