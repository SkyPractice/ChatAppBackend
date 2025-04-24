package com.app.demo.Messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Auth")
public class AuthenticationMessage extends SocketMessage {


    @JsonProperty("username")
    private String userName;

    @JsonProperty("password")
    private String password;

    public AuthenticationMessage(){
        super();
    }

    public AuthenticationMessage(String userName, String password) {
        super(userName);
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
