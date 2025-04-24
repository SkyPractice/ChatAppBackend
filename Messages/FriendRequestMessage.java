package com.app.demo.Messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("FriendReq")
public class FriendRequestMessage extends SocketMessage {

    @JsonProperty("to")
    private String to;

    public FriendRequestMessage() {
        super();
    }

    public FriendRequestMessage(String sender, String to) {
        super(sender);
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
