package com.app.demo.Messages.Servers;


import com.app.demo.Messages.SocketMessage;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Server")
public class ServerMessage extends SocketMessage {

    // Create , Delete
    private String action;
    // when creation
    private String serverName;
    // other actions
    private Integer serverId;

    public ServerMessage(){
        super();
    }

    public ServerMessage(String sender, String action, Integer serverId, String serverName) {
        super(sender);
        this.action = action;
        this.serverId = serverId;
        this.serverName = serverName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public void setServerId(Integer serverUUID) {
        this.serverId = serverUUID;
    }
}
