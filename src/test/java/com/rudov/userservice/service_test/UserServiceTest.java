package com.rudov.userservice.service_test;

import com.rudov.userservice.data.dto.UserDTO;
import com.rudov.userservice.data.entity.UserStatus;
import com.rudov.userservice.repository.UserRepository;
import com.rudov.userservice.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;


@SpringBootTest(classes = {UserServiceImpl.class,UserRepository.class})
public class UserServiceTest {

    @Mock
    UserRepository repository;


    @InjectMocks
    UserServiceImpl service;

    UserDTO test_dto_one = UserDTO.builder().name("Dmitrij")
            .dateBirth(LocalDate.parse("1995-01-20"))
            .age((byte) 27)
            .eMail("rudykan@bk.ru")
            .status(UserStatus.ONLINE)
            .build();

    UserDTO test_dto_two = UserDTO.builder().name("Maria")
            .dateBirth(LocalDate.parse("1998-01-08"))
            .age((byte) 24)
            .eMail("rudovama@bk.ru")
            .status(UserStatus.OFFLINE).build();


    @BeforeEach
    void setup() {


    }

    @Test
    void createUserEntityTest() {
        Mockito.when(service.createUser(test_dto_one)).thenReturn(test_dto_one);
        UserDTO dto = service.createUser(test_dto_one);
        assertEquals(dto, test_dto_one);
    }
}
