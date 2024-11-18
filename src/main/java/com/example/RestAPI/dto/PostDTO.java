package com.example.RestAPI.dto;

import com.example.RestAPI.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {
    private Long id;
    private String caption;
    private String video;
    private String image;
    private String numberOfLikes;
    private UserEntity user;
    private LocalDateTime createdAt;
}
