package com.example.RestAPI.util;

import com.example.RestAPI.dto.PostDTO;
import com.example.RestAPI.dto.UserDTO;
import com.example.RestAPI.entity.PostEntity;
import com.example.RestAPI.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public PostEntity postDTOToPostEntity(PostDTO postDTO) {
        return PostEntity.builder()
                .id(postDTO.getId())
                .video(postDTO.getVideo())
                .userEntity(postDTO.getUser())
                .image(postDTO.getImage())
                .createdAt(postDTO.getCreatedAt())
                .numberOfLikes(postDTO.getNumberOfLikes())
                .caption(postDTO.getCaption())
                .build();
    }

    public PostDTO postEntityToPostDTO(PostEntity postEntity){
        return PostDTO.builder()
                .id(postEntity.getId())
                .video(postEntity.getVideo())
                .image(postEntity.getImage())
                .user(userEntity(postEntity.getUserEntity()))
                .createdAt(postEntity.getCreatedAt())
                .numberOfLikes(postEntity.getNumberOfLikes())
                .caption(postEntity.getCaption())
                .build();
    }

    private UserEntity userEntity(UserEntity userEntity) {
        return UserEntity.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }
}
