package com.app.demo.Services;

import com.app.demo.Repositories.ChannelMsgRepos;
import com.app.demo.Tables.ChannelMessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelMsgService {

    private final ChannelMsgRepos channelMsgRepos;

    @Autowired
    public ChannelMsgService(ChannelMsgRepos channelMsgRepos) {
        this.channelMsgRepos = channelMsgRepos;
    }

    public ChannelMsgRepos getChannelMsgRepos() {
        return channelMsgRepos;
    }

    public List<ChannelMessageEntity> getChannelMsgsByChannelId(Integer channelId){
        return channelMsgRepos.findAll(((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("channelId"), channelId)));
    }
}
