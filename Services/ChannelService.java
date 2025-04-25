package com.app.demo.Services;

import com.app.demo.Repositories.ChannelRepos;
import com.app.demo.Tables.ChannelEntity;
import com.app.demo.Tables.ServerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelService {

    private final ChannelRepos channelRepos;

    @Autowired
    public ChannelService(ChannelRepos channelRepos) {
        this.channelRepos = channelRepos;
    }

    public ChannelRepos getChannelRepos() {
        return channelRepos;
    }

    public List<ChannelEntity> getChannelsByServerId(Integer serverId){
        return channelRepos.findAll(((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("serverId"), serverId)));
    }

    public Integer getServerIdByChannelId(Integer channelId){
        ChannelEntity entity = channelRepos.findById(channelId).orElse(null);
        if(entity != null)
            return entity.getServerId();
        return 0;
    }
}
