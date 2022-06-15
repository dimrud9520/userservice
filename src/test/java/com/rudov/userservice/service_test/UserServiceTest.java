package com.rudov.userservice.service_test;

import com.rudov.userservice.data.dto.Statistic;
import com.rudov.userservice.data.dto.UserDTO;
import com.rudov.userservice.data.entity.UserEntity;
import com.rudov.userservice.data.entity.UserStatus;
import com.rudov.userservice.repository.UserRepository;
import com.rudov.userservice.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(properties = "spring.main.lazy-initialization=true", classes = {UserServiceImpl.class, UserRepository.class})
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

    UserEntity test_entity_one = UserEntity.builder()
            .name("Dmitrij")
            .dateOfBirth(LocalDate.parse("1995-01-20"))
            .eMail("rudykan@bk.ru")
            .status(UserStatus.ONLINE).build();

    UserEntity test_entity_two = UserEntity.builder()
            .name("Maria")
            .dateOfBirth(LocalDate.parse("1998-01-08"))
            .eMail("rudovama@bk.ru")
            .status(UserStatus.OFFLINE).build();


    @BeforeEach
    void setup() {
        Mockito.when(repository.findById(1l)).thenReturn(Optional.ofNullable(test_entity_one));
        Mockito.when(repository.findAll()).thenReturn(List.of(test_entity_one, test_entity_two));
        Mockito.when(repository.findAllByStatus(UserStatus.ONLINE)).thenReturn(List.of(test_entity_one));
    }

    @Test
    void createUserEntityTest() {
        Mockito.when(repository.save(test_entity_one)).thenReturn(test_entity_one);
        UserDTO dto = service.createUser(test_dto_one);
        assertEquals(dto, test_dto_one);
        assertNotEquals(dto, test_dto_two);
    }

    @Test
    void findByIDTest() {
        assertEquals(test_dto_one, service.getUserById(1l));
    }

    @Test
    void findAllTest() {
        List<UserDTO> dtoList = List.of(test_dto_one, test_dto_two);
        assertEquals(dtoList, service.getAllUser());
    }

    @Test
    void updateEntityTest() {
        Mockito.when(repository.save(test_entity_one)).thenReturn(test_entity_two);
        UserDTO updatedDTO = service.updateUser(1l, test_dto_two);
        assertEquals(updatedDTO, test_dto_two);
    }

    @Test
    void statisticTest() {
        Statistic statistic = service.getUserStatistic(UserStatus.ONLINE, Optional.of((byte) 24));
        assertEquals(2, statistic.getUsersToServer());
        assertEquals(1, statistic.getUsersToServerByStatus());
        assertEquals(test_dto_two,statistic.getUsersListByAge().get(0));

    }
}
