package com.app.demo.Services;

import com.app.demo.Repositories.ChannelRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
