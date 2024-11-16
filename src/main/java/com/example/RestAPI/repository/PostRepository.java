package com.example.RestAPI.repository;

import com.example.RestAPI.dto.PostDTO;
import com.example.RestAPI.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {

    @Query(value = "SELECT * FROM post WHERE user_id = :userId", nativeQuery = true)
    List<PostEntity> findPostByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT number_of_likes FROM post WHERE id = :postId", nativeQuery = true)
    Long findAmountOfLikesForPost(Long postId);
}
