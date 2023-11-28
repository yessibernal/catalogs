package com.innter.msdesigncatalog.repositories;

import com.innter.msdesigncatalog.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT U FROM UserEntity U JOIN FETCH U.usersRoles UR JOIN FETCH UR.rol WHERE U.id = :userId")
    UserEntity getAllRolesOfUser(Long userId);

    @Query("SELECT U FROM UserEntity U JOIN FETCH U.usersRoles UR JOIN FETCH UR.rol WHERE U.userName = :userName")
    Optional<UserEntity> getUserWithRoles(String userName);
}
