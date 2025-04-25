package com.app.demo.Messages.Servers;

import com.app.demo.Messages.SocketMessage;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Channel")
public class ChannelMessage extends SocketMessage {

    // Create, Delete
    private String action;
    private String category;
    private Integer channelId;
    private Integer serverId;
    private String channelName;

    public ChannelMessage(){
        super();
    }

    public ChannelMessage(String sender, String action, String channelName, String category, Integer channelId, Integer serverId) {
        super(sender);
        this.action = action;
        this.channelName = channelName;
        this.channelId = channelId;
        this.category = category;
        this.serverId = serverId;
    }

    public ChannelMessage(String action, String channelName, String category, Integer channelId, Integer serverId) {
        this.action = action;
        this.channelName = channelName;
        this.channelId = channelId;
        this.category = category;
        this.serverId = serverId;

    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }
}
