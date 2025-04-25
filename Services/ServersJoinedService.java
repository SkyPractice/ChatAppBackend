package com.app.demo.Services;

import com.app.demo.Repositories.ServersJoinedRepos;
import com.app.demo.Tables.ServerJoinedEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServersJoinedService {

    private final ServersJoinedRepos serversJoinedRepos;

    public ServersJoinedService(ServersJoinedRepos serversJoinedRepos) {
        this.serversJoinedRepos = serversJoinedRepos;
    }

    public ServersJoinedRepos getServersJoinedRepos() {
        return serversJoinedRepos;
    }

    public List<ServerJoinedEntity> getServersJoinedByUserName(String username){
        return serversJoinedRepos.findAll(((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("username"), username)));
    }

    public void deleteByServerId(Integer id){
        serversJoinedRepos.delete(((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("serverId"), id)));
    }

    public List<ServerJoinedEntity> getPeopleJoinedByServerId(Integer id){
        return serversJoinedRepos.findAll(((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("serverId"), id)));
    }
}
