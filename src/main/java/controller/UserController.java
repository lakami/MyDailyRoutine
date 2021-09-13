package controller;


import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.UserRepository;
import request.UserRequest;
import response.UserResponse;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Void> handleNoUser(NoSuchElementException e){
        System.out.println(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String username){
        var user= userRepository.findByUsername(username).orElseThrow();
        UserResponse userResponse = new UserResponse(
                user.getUsername(),
                user.getEmail(),
                user.getUserRole()
        );
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<UserResponse> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(
                        user -> new UserResponse(
                                user.getUsername(),
                                user.getEmail(),
                                user.getUserRole()
                ))
                .collect(Collectors.toList());
    }

}
