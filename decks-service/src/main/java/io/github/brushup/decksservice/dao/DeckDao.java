package io.github.brushup.decksservice.dao;

import java.util.UUID;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Delete;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;

import io.github.brushup.decksservice.entity.Deck;

@Dao
public interface DeckDao {
    
    @Select
    Deck findById(UUID id);

    @Insert
    void save(Deck deck);

    @Delete
    void delete(Deck deck);
}
