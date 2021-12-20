package io.github.brushup.savedcardsservice.entitiy;

import java.util.List;

import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

import lombok.Data;

@Entity
@Data
public class SavedCards {
    
    @PartitionKey
    private String userId;

    private List<String> savedCards;
}
