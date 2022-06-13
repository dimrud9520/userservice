package com.rudov.userservice.repository;

import com.rudov.userservice.data.entity.UserEntity;
import com.rudov.userservice.data.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, CrudRepository<UserEntity, Long> {

    List<UserEntity> findAllByStatus(UserStatus status);



}
