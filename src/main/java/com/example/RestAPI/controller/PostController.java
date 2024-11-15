package com.example.RestAPI.controller;

import com.example.RestAPI.dto.PostDTO;
import com.example.RestAPI.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/post")
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/userId")
    public ResponseEntity<String> createPost(@RequestBody PostDTO postDTO, @RequestParam Long userId) {
        postService.createNewPost(postDTO,userId);
        log.info("post was created:"+postDTO+"user id:"+userId);
        return new ResponseEntity<>("Post was created with userId:"+userId, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePost(@RequestParam Long postId){
        postService.deletePost(postId);
        log.info("post was deleted:"+postId);
        return new ResponseEntity<>("Post was deleted with postId"+postId, HttpStatus.OK);

    }

    @GetMapping("")
    public ResponseEntity<PostDTO> getPostById(@RequestParam Long postId){
        PostDTO getPost=postService.findPostById(postId);
        return new ResponseEntity<>(getPost,HttpStatus.OK);
    }

    @GetMapping("/allPosts")
    public ResponseEntity<List<PostDTO>> getAllPosts(){
        return new ResponseEntity<>(postService.findAllPost(),HttpStatus.OK);
    }

}
