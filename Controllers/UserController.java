package com.app.demo.Controllers;

import com.app.demo.RestDTOS.UserDTO;
import com.app.demo.Services.UserService;
import com.app.demo.Tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class UserController {

    @Value("${file.upload.directory}") // Configure this in your application.properties or application.yml
    private String uploadDirectory;
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users/exists")
    @ResponseBody
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

    @GetMapping("/users/details")
    public User getUserDetails(
            @RequestParam("username") String username
    ){
        User user = userService.getByUserName(username);
        if(user != null){
            user.setPassword("NoAccess");
            return user;
        }

        return null;
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
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<String> postUserMultiPart(@ModelAttribute UserDTO user){


        if(!userService.existsByUsername(user.userName())) {
            User userEntity = new User(
                    user.userName(), user.password(), user.email(), user.notes(),
                    user.userName() + user.file().getOriginalFilename().substring(user.file().getOriginalFilename().lastIndexOf('.'))
            );
            User userr = userService.getUserRepos().save(userEntity);


            File uploadDir = new File(uploadDirectory);
            if(!uploadDir.exists())
                uploadDir.mkdirs();
            try {
                user.file().transferTo(new File(uploadDir.getAbsolutePath() + File.separator + user.userName() + user.file().getOriginalFilename().substring(user.file().getOriginalFilename().lastIndexOf('.'))));
            }
            catch (IOException exception){
                exception.printStackTrace();
            }
            return new ResponseEntity<>("{\"exists\" : false}", HttpStatus.CREATED);
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
