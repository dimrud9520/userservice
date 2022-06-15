package com.rudov.userservice.service.impl;

import com.rudov.userservice.data.dto.Statistic;
import com.rudov.userservice.data.dto.UserDTO;
import com.rudov.userservice.data.entity.UserEntity;
import com.rudov.userservice.data.entity.UserStatus;
import com.rudov.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements com.rudov.userservice.service.abstr.UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public UserDTO createUser(UserDTO userDTO) {
        var entity = UserEntity.builder()
                .name(userDTO.getName())
                .dateOfBirth(userDTO.getDateBirth())
                .eMail(userDTO.getEMail())
                .status(userDTO.getStatus())
                .build();
        repository.save(entity);
        log.debug("entity :{} save to DB", entity);
        return userDTO;
    }

    public UserDTO getUserById(Long id) {
        var userEntity = repository.findById(id);
        if (!userEntity.isEmpty()) {
            log.debug("entity get:{}", userEntity);
            return userEntity.get().toDTO(userEntity.get());
        }
        log.debug("user not found:{}", id);
        return null;
    }

    @Override
    public List<UserDTO> getAllUser() {
        return repository.findAll().stream().map(entity -> entity.toDTO(entity)).collect(Collectors.toList());

    }

    @Override
    public void deleteById(Long id) {
        if (!repository.findById(id).isEmpty()) {
            log.debug("entity by id :{} not delete(not found)", id);
        }
        repository.deleteById(id);
        log.debug("entity by id:{} deleted", id);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO dto) {
        var entity = repository.findById(id);
        if (!entity.isEmpty()) {
            UserEntity updateEntity = entity.get();
            updateEntity.setName(dto.getName());
            updateEntity.setEMail(dto.getEMail());
            updateEntity.setDateOfBirth(dto.getDateBirth());
            updateEntity.setStatus(dto.getStatus());
            repository.save(updateEntity);
            log.debug("entity id:{} update", updateEntity.getId());
            return updateEntity.toDTO(updateEntity);
        } else {
            log.debug("entity by id :{} not found", id);
            return null;
        }
    }

    /**
     * Метод меняет статутс пользователя
     * @param status новый статус
     * @param id ид пользователя, которому меняется статус
     * @return MAP:
     * {
     *     ID : ид пользователя
     *     Old status : старый статус пользователя
     *     New status : новый статус пользователя
     * }
     */
    @Override
    public Map<String, Serializable> changeStatusUserById(UserStatus status, Long id) {
        UserDTO dto = null;
        dto = getUserById(id);
        if (dto != null) {
            Map<String, Serializable> result = new HashMap<>();
            result.put("ID", id);
            result.put("Old status", dto.getStatus());
            dto.setStatus(status);
            result.put("New status", dto.getStatus());
            updateUser(id, dto);
            return result;
        } else {
            log.debug("entity by id:{} not change status(not found)", id);
            return Collections.emptyMap();
        }
    }

    /**
     * Данный метод пердоставляет статисткику сервера
     * @param status предоставляет список пользователей по указаному параметру
     * @param age в случае если задан параметр возраста, предоставляет список пользователей указанного возраста
     * @return Статистика сервера
     */
    @Override
    public Statistic getUserStatistic(UserStatus status, Optional<Byte> age) {
        var statistic = Statistic.builder();
        var findByStatus = repository.findAllByStatus(status);
        var findAllByServer = getAllUser();
        statistic.usersToServer(findAllByServer.size());
        statistic.usersToServerByStatus(findByStatus.size());
        var averageAge = findAllByServer.stream().mapToInt(user -> user.getAge()).average();
        statistic.averageAge((int) averageAge.getAsDouble());
        if (!age.isEmpty()) {
            var ageUsers = findAllByServer.stream().filter(userDTO -> userDTO.getAge().equals(age.get())).collect(Collectors.toList());
            statistic.usersListByAge(ageUsers);
        }
        return statistic.build();

    }


}
