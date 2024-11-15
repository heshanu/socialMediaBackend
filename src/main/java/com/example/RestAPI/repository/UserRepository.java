package com.example.RestAPI.repository;
import com.example.RestAPI.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity,Long> {

@Query(value = "SELECT u FROM suser u WHERE u.email = :email",nativeQuery = true)
Optional<UserEntity> findUserByEmail(@Param("email") String email);

@Query(value = "SELECT * FROM suser u WHERE u.id = :id",nativeQuery = true)
Optional<UserEntity> findUserById(@Param("id") Long id);

@Query(value = "SELECT * FROM suser b WHERE b.first_name = :firstName", nativeQuery = true)
List<UserEntity> searchUserFirstName(@Param("firstName") String firstName);

@Modifying
@Query(value = "UPDATE suser  SET first_name = :firstName, last_name = :lastName, email = :email, password = :password WHERE id = :id", nativeQuery = true)
void updateUserById(@Param("id") Long id, @Param("firstName") String firstName, @Param("lastName") String lastName,
                        @Param("email") String email, @Param("password") String password);

}
