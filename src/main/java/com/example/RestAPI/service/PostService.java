package com.example.RestAPI.service;

import com.example.RestAPI.dto.PostDTO;

import java.util.List;

public interface PostService {
    void createNewPost(PostDTO postDTO,Long userId);
    void deletePost(Long postId);
    List<PostDTO> findPostByUserId(Long userId);
    PostDTO findPostById(Long postId);
    List<PostDTO> findAllPost();
    Long likedPost(Long postId);
}
