package com.example.RestAPI.repository;

import com.example.RestAPI.entity.BookEntity;
import com.example.RestAPI.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

@Query(value = "SELECT u FROM suser u WHERE u.email = :email",nativeQuery = true)
Optional<UserEntity> findUserByEmail(@Param("email") String email);

@Query(value = "SELECT u FROM suser u WHERE u.id = :id",nativeQuery = true)
Optional<UserEntity> findUserById(@Param("id") Long id);

}
