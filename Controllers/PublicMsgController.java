package com.app.demo.Controllers;

import com.app.demo.Services.PublicMsgService;
import com.app.demo.Tables.PublicMessageEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

// Posting Msgs Is Through WebSockets Only
@RestController
public class PublicMsgController {

    private final PublicMsgService publicMsgService;

    public PublicMsgController(PublicMsgService publicMsgService) {
        this.publicMsgService = publicMsgService;
    }

    @GetMapping("/publicmsgs")
    @Async
    public CompletableFuture<ResponseEntity<List<PublicMessageEntity>>> getPublicMsgs(){
        return CompletableFuture.completedFuture(new ResponseEntity<>
                (publicMsgService.getPublicMsgRepos().findAll(), HttpStatus.OK));
    }

}
