package com.example.RestAPI.service.impl;

import com.example.RestAPI.dto.PostDTO;
import com.example.RestAPI.dto.UserDTO;
import com.example.RestAPI.entity.PostEntity;
import com.example.RestAPI.entity.UserEntity;
import com.example.RestAPI.exception.PostCreationException;
import com.example.RestAPI.exception.PostNotFoundException;
import com.example.RestAPI.exception.UserNotFoundException;
import com.example.RestAPI.repository.PostRepository;
import com.example.RestAPI.repository.UserRepository;
import com.example.RestAPI.service.PostService;
import com.example.RestAPI.util.PostMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository,
                           UserRepository userRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postMapper = postMapper;
    }

    PostDTO postEntityToPostDTO(PostEntity postEntity){
        return PostDTO.builder()
                .caption(postEntity.getCaption())
                .user(postEntity.getUserEntity())
                .image(postEntity.getImage())
                .numberOfLikes(postEntity.getNumberOfLikes())
                .video(postEntity.getVideo())
                .createdAt(postEntity.getCreatedAt())
                .build();
    }

    PostEntity postDTOToPostEntity(PostDTO postDTO){
        return PostEntity.builder()
                .video(postDTO.getVideo())
                .userEntity(postDTO.getUser())
                .image(postDTO.getImage())
                .createdAt(postDTO.getCreatedAt())
                .caption(postDTO.getCaption())
                .numberOfLikes(postDTO.getNumberOfLikes())
                .build();
    }

    @Override
    public void createNewPost(PostDTO postDTO,Long userId) {
        UserEntity userEntity=  userRepository.findUserById(userId)
                .orElseThrow(()->new PostNotFoundException("Unable to find user"+userId,"UNABLE_TO_FIND_USER"));
        try{
            PostEntity newPost=PostEntity.builder()
                    .id(postDTO.getId())
                    .caption(postDTO.getCaption())
                    .createdAt(LocalDateTime.now())
                    .image(postDTO.getImage())
                    .userEntity(userEntity)
                    .video(postDTO.getVideo())
                    .numberOfLikes(postDTO.getNumberOfLikes())
                    .build();

            log.info("create new post",newPost);
            postRepository.save(newPost);
            log.info("Post is saved under postId:"+ userEntity.getId()+"User Id:"+userId);
        }
        catch (Exception e){
            throw new PostCreationException("Unable to create post","UNABLE_TO_CREATE_POST");
        }

    }

    @Override
    public void deletePost(Long postId) {
            Optional<PostEntity> deletePost= Optional.ofNullable(postRepository.findById(postId)
                    .orElseThrow(() -> new PostNotFoundException("Unable to find post using this id:" + postId, "UNABLE_TO_DELETE")));
            try {
                Long deletedPostId=deletePost.get().getId();
                postRepository.deleteById(deletedPostId);
                log.info("post deleted id:"+postId);
            }catch (Exception e){
                e.printStackTrace();;
            }
    }

    @Override
    public List<PostDTO> findPostByUserId(Long userId) {
        log.info("userId"+userId);
        try{
        List<PostEntity> postEntityList =postRepository.findPostByUserId(userId);
        log.info(":"+postEntityList);

        return postEntityList
                .stream()
                    .map(postMapper::postEntityToPostDTO)
                    .collect(Collectors.toList());}
        catch (Exception e){
            throw new PostCreationException("Unable to find user by id"+userId,"UNABLE_TO_FINDUSER");
        }
    }

    @Override
    public PostDTO findPostById(Long postId) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Unable to find post", "UNABLE_TO_FIND_POST"));

        log.info("Post found with ID: {}", postId);

        return postEntityToPostDTO(postEntity);
    }

    @Override
    public List<PostDTO> findAllPost() {
        try {
            List<PostEntity> existingPosts = postRepository.findAll();
            if (existingPosts.isEmpty()) {
                throw new PostCreationException("Cannot find posts, db is empty", "UNABLE_TO_SEARCH_POSTS");
            }
            log.info("Posts fetched: {}", existingPosts.size());
            return existingPosts.stream()
                    .map(postMapper::postEntityToPostDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new UserNotFoundException("Cannot find posts, db is empty", "UNABLE_TO_SEARCH_POSTS");
        }
    }

    @Override
    public Long likedPost(Long postId) {
        PostEntity likedPost = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Unable to find post", "UNABLE_TO_FIND_POST"));
        return postRepository.findAmountOfLikesForPost(postId);
    }
}
