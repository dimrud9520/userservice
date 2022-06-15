package com.rudov.userservice.data.entity;

public interface AbstractEntity<Entity, DTO> {
    /**
     * Данный метод необходимо реализовать всем Entity для преобразования их в DTO
     * @param entity класс сущности
     * @return объект DTO аналогичный Entity
     */
    DTO toDTO(Entity entity);
}
