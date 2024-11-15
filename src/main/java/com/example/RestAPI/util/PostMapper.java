package com.example.RestAPI.util;

import com.example.RestAPI.dto.PostDTO;
import com.example.RestAPI.entity.PostEntity;

public class PostMapper {
    public PostEntity postDTOToPostEntity(PostDTO postDTO) {
        return PostEntity.builder()
                .video(postDTO.getVideo())
                .userEntity(postDTO.getUser())
                .image(postDTO.getImage())
                .createdAt(postDTO.getCreatedAt())
                .caption(postDTO.getCaption())
                .build();
    }
}
