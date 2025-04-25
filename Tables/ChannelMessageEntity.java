package com.app.demo.Tables;

import jakarta.persistence.*;

@Entity
@Table(name = "ChannelMessages")
public class ChannelMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer channelId;
    private String content;
    private String sender;

    public ChannelMessageEntity(String content, String sender, Integer channelId) {
        this.content = content;
        this.sender = sender;
        this.channelId = channelId;
    }

    public ChannelMessageEntity() {
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

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Integer getId() {
        return id;
    }
}
