package com.example.RestAPI.service.impl;

import com.example.RestAPI.dto.UserDTO;
import com.example.RestAPI.entity.UserEntity;
import com.example.RestAPI.exception.UserNotFoundException;
import com.example.RestAPI.repository.UserRepository;
import com.example.RestAPI.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    UserDTO userEntityToUserDTO(UserEntity userEntity){
        return UserDTO.builder()
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword()).build();
    }

    UserEntity userDTOToUserEntity(UserDTO userDTO){
        return UserEntity.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword()).build();
    }

    @Override
    public UserDTO userSave(UserDTO userDTO) {
       UserEntity userEntity=UserEntity.builder()
               .firstName(userDTO.getFirstName())
               .lastName(userDTO.getLastName())
               .email(userDTO.getEmail())
               .password(userDTO.getPassword()).build();

       UserEntity user= userRepository.save(userEntity);
       return userEntityToUserDTO(user);
    }

    @Override
    public UserEntity findUserById(Long id){
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Couldn't find user", "COULDNT_FIND_USER"));
      return userEntity;
    }

    @Override
    public List<UserDTO> showAllUsers() {
            List<UserEntity> usrs = userRepository.findAll();
        if (usrs.isEmpty()) {
            throw new UserNotFoundException("No users found", "NO_USERS_FOUND");
        }
        return (List<UserDTO>) usrs.stream()
                    .map(this::userEntityToUserDTO)
                    .collect(Collectors.toList());
    }

    @Override
    public void deleteByUserId(Long id) {
        UserEntity existingEmployee = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("Unable to delete employee with id:"+id, "UNABLE_TO_DELETE"));
        userRepository.deleteById(id);
        log.info("Employee deleted by id: {}:", id);
    }

    @Override
    public UserEntity findUserByEmail(String email) {
        try {
            return userRepository.findUserByEmail(email)
                    .orElseThrow(() -> new UserNotFoundException("Unable to find user by this email: " + email, "UNABLE_TO_FIND_EMAIL"));
        } catch (Exception e) {
            // Handle other exceptions if needed
            throw new RuntimeException("An error occurred while fetching the user by email: " + email, e);
        }
    }

}