package com.app.demo.Tables;

import jakarta.persistence.*;

@Entity
@Table(name = "ServersJoined", indexes = {
        @Index(name = "idx_user_name_server_joined", columnList = "username"),
        @Index(name = "idx_server_name", columnList = "serverName")

})
public class ServerJoinedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String serverName;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    private Integer serverId;

    public ServerJoinedEntity(String username, Integer serverId, String serverName) {
        this.username = username;
        this.serverId = serverId;
        this.serverName = serverName;
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
