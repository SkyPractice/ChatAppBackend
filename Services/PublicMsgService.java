package com.app.demo.Services;

import com.app.demo.Repositories.PublicMsgRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicMsgService {

    private final PublicMsgRepos publicMsgRepos;

    @Autowired
    public PublicMsgService(PublicMsgRepos publicMsgRepos) {
        this.publicMsgRepos = publicMsgRepos;
    }

    public PublicMsgRepos getPublicMsgRepos() {
        return publicMsgRepos;
    }
}
