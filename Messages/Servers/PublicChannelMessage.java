package com.app.demo.Messages.Servers;

import com.app.demo.Messages.SocketMessage;
import com.fasterxml.jackson.annotation.JsonTypeName;


@JsonTypeName("ChannelMsg")
public class PublicChannelMessage extends SocketMessage {

    private Integer channelId;
    private String content;

    public PublicChannelMessage(){
        super();
    }

    public PublicChannelMessage(Integer channelId, String content) {
        this.content = content;
        this.channelId = channelId;
    }

    public PublicChannelMessage(String sender, Integer channelId, String content) {
        super(sender);
        this.content = content;
        this.channelId = channelId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }
}
