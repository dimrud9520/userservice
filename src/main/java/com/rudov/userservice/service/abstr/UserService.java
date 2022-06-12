package com.rudov.userservice.service.abstr;

import com.rudov.userservice.data.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUser();

    void deleteById(Long id);

    UserDTO updateUser(Long id,UserDTO updateUser);


}
