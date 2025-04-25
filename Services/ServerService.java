package com.app.demo.Services;

import com.app.demo.Repositories.ServerRepos;
import com.app.demo.Tables.ServerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerService {

    private final ServerRepos serverRepos;

    @Autowired
    public ServerService(ServerRepos serverRepos) {
        this.serverRepos = serverRepos;
    }

    public ServerRepos getServerRepos() {
        return serverRepos;
    }

    public ServerEntity getServerByName(String name){
        return serverRepos.findOne(((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name))).orElse(null);
    }
}
