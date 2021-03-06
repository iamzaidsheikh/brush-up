package io.github.brushup.decksservice.entity;

import java.time.Instant;
import java.util.List;

import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

import lombok.Data;

@Entity
@Data
public class Deck {
    
    @PartitionKey
    private String id;

    private UserUDT author;
    
    private String title;

    private String description;

    private int numStars;

    private int numSaves;

    private int numCards;

    private List<String> cards;
    
    private Instant dateCreated;

    private Instant dateUpdated;

    private List<String> savedBy;
}
