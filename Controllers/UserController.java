package com.app.demo.Controllers;

import com.app.demo.RestDTOS.UserDTO;
import com.app.demo.Services.UserService;
import com.app.demo.Tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
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
    public ResponseEntity<String> postUser(
            @RequestBody User user
    ){
        if(!userService.existsByUsername(user.getUserName())) {
            userService.getUserRepos().save(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>("{\"exists\" : true}", HttpStatus.FOUND);
    }

    @PostMapping("/users/multipart")
    public ResponseEntity<String> postUserMultiPart(@ModelAttribute UserDTO user){
        User userEntity = new User(
                user.userName(), user.password(), user.email(), user.country(), user.notes()
        );

        if(!userService.existsByUsername(userEntity.getUserName())) {
            User userr = userService.getUserRepos().save(userEntity);

        try {
            user.file().transferTo(new File("/static/" + userr.getId().toString().substring(userr.getId().toString().lastIndexOf('.'))));
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>("{\"exists\" : true}", HttpStatus.FOUND);
    }

    @DeleteMapping("/users")
    public ResponseEntity<Void> deleteUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ){
        userService.deleteByUsernameAndPassword(username, password);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/users")
    public ResponseEntity<Void> putUser(
            @RequestBody User user
    ){
        userService.getUserRepos().save(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
