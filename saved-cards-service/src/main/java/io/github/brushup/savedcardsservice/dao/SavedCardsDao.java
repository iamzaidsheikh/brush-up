package io.github.brushup.savedcardsservice.dao;

import java.util.Optional;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Delete;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import com.datastax.oss.driver.api.mapper.annotations.Update;

import io.github.brushup.savedcardsservice.entitiy.SavedCards;

@Dao
public interface SavedCardsDao {
    
    @Select
    Optional<SavedCards> findById(String id);

    @Insert
    void save(SavedCards savedCards);

    @Delete
    void delete(SavedCards savedCards);

    @Update
    void update(SavedCards savedCards);
}
