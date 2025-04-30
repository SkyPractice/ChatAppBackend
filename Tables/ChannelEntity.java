package com.app.demo.Tables;


import jakarta.persistence.*;

@Entity
@Table(name = "Channels",
    indexes = {
        @Index(name = "serverIdIndex", columnList = "serverId")
    })
public class ChannelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer serverId;
    private String name;
    private String category;

    public ChannelEntity(){

    }

    public ChannelEntity(String category, String name, Integer serverId) {
        this.category = category;
        this.name = name;
        this.serverId = serverId;
    }

    public Integer getId(){
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }
}
