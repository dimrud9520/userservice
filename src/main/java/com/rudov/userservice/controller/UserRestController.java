package com.rudov.userservice.controller;

import com.rudov.userservice.data.dto.Statistic;
import com.rudov.userservice.data.dto.UserDTO;
import com.rudov.userservice.data.entity.UserStatus;
import com.rudov.userservice.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserRestController {
    private final UserServiceImpl userService;

    @Autowired
    public UserRestController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Tag(name = "REST-USER-POST")
    @Operation(summary = "Create User")
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        try {
            userService.createUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("REST ERROR: NO CREATED USER! :{}", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Tag(name = "REST-USER-GET")
    @Operation(summary = "Find user by id")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        UserDTO user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            log.warn("REST WARN:user not found:{}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Tag(name = "REST-USER-GET")
    @Operation(summary = "Find all users")
    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> getAllUser() {
        List<UserDTO> list = new ArrayList<>();
        try {
            userService.getAllUser().stream().forEach(list::add);
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error("REST ERROR: NO FIND ALL USER! :{}", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Tag(name = "REST-USER-PUT")
    @Operation(summary = "Update user by id")
    @RequestMapping(value = "/user/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserDTO> updateUserById(@PathVariable("id") Long id, @RequestBody UserDTO user) {
        UserDTO updatedUser = null;
        updatedUser = userService.updateUser(id, user);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            log.warn("REST WARNING:cannot be updated, such user id:{} does not exist", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Tag(name = "REST-USER-PUT")
    @Operation(summary = "Change user status")
    @RequestMapping(value = "/user/update/status/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, Serializable>> changeUserStatusById(@PathVariable("id") Long id, @RequestParam("status") UserStatus status) {
        var response = userService.changeStatusUserById(status, id);
        if (!response.isEmpty()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

    }

    @Tag(name = "REST-USER-DELETE")
    @Operation(summary = "Delete user by id")
    @RequestMapping(value = "/user/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long id) {
        UserDTO dto = null;
        dto = userService.getUserById(id);
        if (dto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Tag(name = "REST-USER-GET")
    @Operation(summary = "Get statistic server")
    @RequestMapping(value = "/user/statistic", method = RequestMethod.GET)
    public ResponseEntity<Statistic> getUserStatistic(@RequestParam("status") UserStatus status,
                                                      @RequestParam(required = false) Byte age) {
        try {
            Statistic statistic;
            if (age != null) {
                statistic = userService.getUserStatistic(status, Optional.of(age));
            } else {
                statistic = userService.getUserStatistic(status, Optional.ofNullable(null));
            }
            return new ResponseEntity<>(statistic, HttpStatus.OK);
        } catch (Exception e) {
            log.error("REST ERROR: statistic exception", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
