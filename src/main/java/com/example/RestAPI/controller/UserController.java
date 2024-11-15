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
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/user/")
@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("firstName")
    public ResponseEntity<List<UserDTO>> showUserByFirstName(@RequestParam String firstName){
        List<UserDTO> filteredUser=userService.findUserByFirstName(firstName);
        log.info("users are:"+filteredUser);
        return new ResponseEntity<>(filteredUser,HttpStatus.OK);
    }

   @GetMapping("id")
    public ResponseEntity<UserEntity> showUserById(@RequestParam Long id){
        UserEntity usr = this.userService.findUserById(id);
        log.info("user is:"+usr);
        return new ResponseEntity<>(usr, HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateUserById(@PathVariable Long id,@RequestBody UserDTO userDTO) {
        try {
            userService.updateUser(id,userDTO);
            return ResponseEntity.ok("User updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user:"+id);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteByUserId(@PathVariable Long id){
        userService.deleteByUserId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
