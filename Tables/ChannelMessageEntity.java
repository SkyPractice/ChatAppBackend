package com.app.demo.Tables;

import jakarta.persistence.*;

@Entity
@Table(name = "ChannelMessages",
    indexes = {
        @Index(name = "channelIdIndex", columnList = "channelId")
    }
)
public class ChannelMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer channelId;
    private String content;
    private String sender;
    private String senderImg;

    public ChannelMessageEntity(String content, String sender, Integer channelId, String senderImg) {
        this.content = content;
        this.sender = sender;
        this.channelId = channelId;
        this.senderImg = senderImg;
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

    public String getSenderImg() {
        return senderImg;
    }

    public void setSenderImg(String senderImg) {
        this.senderImg = senderImg;
    }
}
