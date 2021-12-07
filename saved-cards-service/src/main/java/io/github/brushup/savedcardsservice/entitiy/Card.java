package io.github.brushup.savedcardsservice.entitiy;

import java.time.Instant;
import java.util.List;

import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

import lombok.Data;

@Entity
@Data
public class Card {
    
    @PartitionKey
    private String id;

    private UserUDT author;

    private String front;

    private String back;

    private int numSaves;

    private Instant dateCreated;

    private List<UserUDT> savedBy;
}

