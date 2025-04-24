package com.app.demo.Messages;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("PublicMsg")
public class PublicMessage extends SocketMessage {

    private String content;

    PublicMessage(){
        super();
    }

    public PublicMessage(String sender, String content){
        super(sender);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
