package com.rudov.userservice.service;

import com.rudov.userservice.data.dto.UserDTO;
import com.rudov.userservice.data.entity.UserEntity;
import com.rudov.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDTO createUser(UserEntity entity) {
        userRepository.save(entity);
        log.debug("entity :{} save to DB", entity);
        return entity.toDTO(entity);
    }

    public UserDTO getUserById(Long id) {
        var userEntity = userRepository.findById(id).get();
        log.debug("entity get:{}", userEntity);
        return userEntity.toDTO(userEntity);
    }

}
