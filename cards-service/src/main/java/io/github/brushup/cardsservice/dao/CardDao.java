package io.github.brushup.cardsservice.dao;

import java.util.Optional;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Delete;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;

import io.github.brushup.cardsservice.entity.Card;

@Dao
public interface CardDao {
    
    @Select
    Optional<Card> findById(String id);

    @Insert
    void save(Card card);

    @Delete
    void delete(Card card);
}