package com.app.demo.Services;

import com.app.demo.Repositories.ChannelMsgRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
