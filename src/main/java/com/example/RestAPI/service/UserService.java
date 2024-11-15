package com.example.RestAPI.service;
import com.example.RestAPI.dto.UserDTO;
import com.example.RestAPI.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserDTO userSave(UserDTO userDTO);
    UserEntity findUserById(Long id);
    List<UserDTO> showAllUsers();
    void deleteByUserId(Long id);
    UserEntity findUserByEmail(String email);
    List<UserDTO> findUserByFirstName(String firstName);
    void updateUser(Long id, UserDTO userDTO);

}
