package io.github.brushup.decksservice.entity;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

import lombok.Data;

@Entity
@Data
public class Card {
    
    @PartitionKey
    private UUID id;

    private UserUDT author;

    private String front;

    private String back;

    private int numSaves;

    private Instant dateCreated;

    private List<UserUDT> savedBy;
}
