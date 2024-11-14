package com.example.RestAPI.service;
import com.example.RestAPI.dto.UserDTO;
import com.example.RestAPI.entity.UserEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO userSave(UserDTO userDTO);
    UserEntity findUserById(Long id);
    List<UserDTO> showAllUsers();
    void deleteByUserId(Long id);
    UserEntity findUserByEmail(String email);
}
