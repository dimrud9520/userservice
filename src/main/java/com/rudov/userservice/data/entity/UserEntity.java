package com.rudov.userservice.data.entity;


import com.rudov.userservice.data.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;


@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements AbstractEntity<UserEntity, UserDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "e_mail")
    private String eMail;

    @Column(name = "status")
    private Boolean status;

    @Override
    public UserDTO toDTO(UserEntity entity) {
        return UserDTO.builder()
                .name(entity.getName())
                .eMail(entity.getEMail())
                .dateBirth(entity.getDateOfBirth())
                .age((byte) Period.between(entity.getDateOfBirth(), LocalDate.now()).getYears())
                .eMail(entity.getEMail())
                .isOnline(entity.getStatus())
                .build();
    }

}