package com.rudov.userservice.service.abstr;

import com.rudov.userservice.data.dto.Statistic;
import com.rudov.userservice.data.dto.UserDTO;
import com.rudov.userservice.data.entity.UserStatus;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUser();

    void deleteById(Long id);

    UserDTO updateUser(Long id, UserDTO updateUser);

    Map<String, Serializable> changeStatusUserById(UserStatus status, Long id);

    Statistic getUserStatistic(UserStatus status, Optional<Byte> age);


}
