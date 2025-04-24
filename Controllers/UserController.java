package com.app.demo.Controllers;

import com.app.demo.Services.UserService;
import com.app.demo.Tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users/{index}")
    public ResponseEntity<User> getUser(
            @PathVariable("index") int id
    ){
        if(userService.getUserRepos().count() < id){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userService.getUserRepos().findById(id).orElse(null), HttpStatus.FOUND);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userService.getUserRepos().findAll(), HttpStatus.OK);
    }

    @GetMapping("/users/exists")
    public ResponseEntity<String> userExists(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ){

        String response;
        if(userService.existsByUsernameAndPassword(username, password)){
            response = "{\"exists\" : true}";
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        }
        else {
            response = "{\"exists\" : false}";
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/users")
    public ResponseEntity<Void> postUser(
            @RequestBody User user
    ){
        userService.getUserRepos().save(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/users/{index}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable("index") int id
    ){
        if(userService.getUserRepos().count() < id){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.getUserRepos().deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/users/{index}")
    public ResponseEntity<Void> putUser(
            @PathVariable("index") int id,
            @RequestBody User user
    ){
        userService.getUserRepos().save(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
