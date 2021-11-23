package io.github.brushup.decksservice.dao;

import java.util.Optional;
import java.util.UUID;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Delete;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import com.datastax.oss.driver.api.mapper.annotations.Update;

import io.github.brushup.decksservice.entity.Deck;

@Dao
public interface DeckDao {
    
    @Select
    Optional<Deck> findById(UUID id);

    @Insert
    void save(Deck deck);

    @Update
    void update(Deck deck);

    @Delete
    void delete(Deck deck);
}
