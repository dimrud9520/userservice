package com.rudov.userservice.data.entity;

public interface  AbstractEntity<Entity,DTO> {
    /**
     * Метод преобразует Entity в DTO и наоборот
     * @param entity

     * @return объект DTO
     */

     DTO toDTO(Entity entity);
}
