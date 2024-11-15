package com.example.RestAPI.repository;

import com.example.RestAPI.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {
}
