package com.app.demo.Controllers;

import com.app.demo.Services.PrivateMsgService;
import com.app.demo.Tables.PrivateMessageEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class PrivateMsgController {
    private final PrivateMsgService privateMsgService;

    public PrivateMsgController(PrivateMsgService privateMsgService) {
        this.privateMsgService = privateMsgService;
    }

    @GetMapping("/privatemsgs")
    @Async
    public CompletableFuture<List<PrivateMessageEntity>> privateMessageEntities(
            @RequestParam("first") String first,
            @RequestParam("second") String second
    ){
        return CompletableFuture.completedFuture(privateMsgService.getPrivateMessagesByName(first, second));
    }
}
