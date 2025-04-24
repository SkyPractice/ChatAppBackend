package com.app.demo.Messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("PrivateMsg")
public class PrivateMessage extends SocketMessage {

    @JsonProperty("content")
    private String content;

    @JsonProperty("to")
    private String to;

    PrivateMessage(){
        super();
    }

    public PrivateMessage(String sender, String content, String to) {
        super(sender);
        this.content = content;
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
