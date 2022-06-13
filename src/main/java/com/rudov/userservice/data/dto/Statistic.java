package com.rudov.userservice.data.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Statistic {

    private Integer usersToServer;
    private Integer usersToServerByStatus;
    private Integer averageAge;
    private List<UserDTO> usersListByAge;
}
