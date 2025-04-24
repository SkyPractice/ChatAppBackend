package com.app.demo.Messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("FriendReqRes")
public class FriendRequestResponseMessage extends SocketMessage{

    @JsonProperty("accepted")
    private Boolean accepted;
    @JsonProperty("to")
    private String to;

    public FriendRequestResponseMessage() {
        super();
    }

    public FriendRequestResponseMessage(String sender, Boolean accepted, String to) {
        super(sender);
        this.accepted = accepted;
        this.to = to;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
