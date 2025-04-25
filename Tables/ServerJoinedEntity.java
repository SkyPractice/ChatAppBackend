package com.app.demo.Tables;

import jakarta.persistence.*;

@Entity
@Table(name = "ServersJoined")
public class ServerJoinedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private Integer serverId;

    public ServerJoinedEntity(String username, Integer serverId) {
        this.username = username;
        this.serverId = serverId;
    }

    public ServerJoinedEntity() {
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }
}
