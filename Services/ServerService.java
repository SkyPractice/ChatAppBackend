package com.app.demo.Services;

import com.app.demo.Repositories.ServerRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerService {

    private final ServerRepos serverRepos;

    @Autowired
    public ServerService(ServerRepos serverRepos) {
        this.serverRepos = serverRepos;
    }
}
