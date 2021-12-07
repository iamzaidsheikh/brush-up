package io.github.brushup.savedcardsservice.entitiy;

import java.util.List;

import com.datastax.oss.driver.api.mapper.annotations.Entity;

import lombok.Data;

@Entity
@Data
public class SavedCards {
    
    private String id;

    private List<CardUDT> cards;
}
