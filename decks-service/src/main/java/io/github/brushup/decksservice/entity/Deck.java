package io.github.brushup.decksservice.entity;

import java.util.List;
import java.util.UUID;

import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

import lombok.Data;

@Entity
@Data
public class Deck {
    
    @PartitionKey
    private UUID id;

    private int numCards;

    private List<Card> cards;    
}
