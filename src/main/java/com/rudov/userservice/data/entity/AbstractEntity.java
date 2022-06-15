package com.rudov.userservice.data.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class AbstractEntity<Entity, DTO> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Данный метод необходимо реализовать всем Entity для преобразования их в DTO
     *
     * @param entity класс сущности
     * @return объект DTO аналогичный Entity
     */
    public abstract DTO toDTO(Entity entity);
}
