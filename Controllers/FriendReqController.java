package com.app.demo.Controllers;

import com.app.demo.Repositories.FriendRequestRepos;
import com.app.demo.Services.FriendRequestService;
import com.app.demo.Tables.FriendRequestEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FriendReqController {
    private final FriendRequestService friendRequestService;

    public FriendReqController(FriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }

    @GetMapping("/friends/accepted/{username}")
    public List<FriendRequestEntity> getAcceptedFriendRequestsByName(
            @PathVariable("username") String username
    ){
        return friendRequestService.acceptedFriendRequestsBySingleName(username);
    }
    @GetMapping("/friends/pending/{username}")
    public List<FriendRequestEntity> getPendingFriendRequestsByName(
            @PathVariable("username") String username
    ){
        return friendRequestService.pendingFriendRequestsBySingleName(username);
    }

}
