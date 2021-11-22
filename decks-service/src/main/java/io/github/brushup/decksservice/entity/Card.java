package io.github.brushup.decksservice.entity;

import java.util.UUID;

import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

import lombok.Data;

@Entity
@Data
public class Card {
    
    @PartitionKey
    private UUID id;

    private String front;

    private String back;
}
