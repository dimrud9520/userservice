package com.rudov.userservice.data.entity;

public interface AbstractEntity<Entity, DTO> {

    DTO toDTO(Entity entity);
}
