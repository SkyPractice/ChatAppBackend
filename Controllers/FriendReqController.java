package com.app.demo.Controllers;

import com.app.demo.Repositories.FriendRequestRepos;
import com.app.demo.Services.FriendRequestService;
import com.app.demo.Tables.FriendRequestEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class FriendReqController {
    private final FriendRequestService friendRequestService;

    public FriendReqController(FriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }

    @GetMapping("/friends/accepted/{username}")
    @Async
    public CompletableFuture<List<FriendRequestEntity>> getAcceptedFriendRequestsByName(
            @PathVariable("username") String username
    ){
        return CompletableFuture.completedFuture(
                friendRequestService.acceptedFriendRequestsBySingleName(username));
    }
    @GetMapping("/friends/pending/{username}")
    @Async
    public CompletableFuture<List<FriendRequestEntity>> getPendingFriendRequestsByName(
            @PathVariable("username") String username
    ){
        return CompletableFuture.completedFuture(
                friendRequestService.pendingFriendRequestsBySingleName(username));
    }

}
