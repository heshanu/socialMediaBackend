package com.example.RestAPI.controller;

import com.example.RestAPI.dto.UserDTO;
import com.example.RestAPI.entity.UserEntity;
import com.example.RestAPI.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/v1/user/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO user= userService.userSave(userDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("emails")
    public ResponseEntity<UserEntity> getUserByEmail(@RequestParam String email){
        UserEntity user = userService.findUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }
    @GetMapping("users")
    public ResponseEntity<List<UserDTO>> showAllUsers(){
        List<UserDTO> usrs=this.userService.showAllUsers();
        return new ResponseEntity<>(usrs,HttpStatus.OK);
    }

   @GetMapping("id")
    public ResponseEntity<UserEntity> showUserById(@RequestParam Long id){
        UserEntity usr = this.userService.findUserById(id);
        log.info("user is:"+usr);
        return new ResponseEntity<>(usr, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteByUserId(@PathVariable Long id){
        userService.deleteByUserId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
