package io.github.brushup.decksservice.entity;

import java.time.Instant;
import java.util.UUID;

import com.datastax.oss.driver.api.mapper.annotations.Entity;

import lombok.Data;

@Entity
@Data
public class CardUDT {
    
    private UUID id;

    private UserUDT author;

    private String front;

    private String back;

    private int numSaves;

    private Instant dateCreated;
}
