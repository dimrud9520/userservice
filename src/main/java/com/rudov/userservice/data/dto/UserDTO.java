package com.rudov.userservice.data.dto;

import com.rudov.userservice.data.entity.UserStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserDTO {
    private String name;
    private LocalDate dateBirth;
    private Byte age;
    private String eMail;
    private UserStatus status;


}
