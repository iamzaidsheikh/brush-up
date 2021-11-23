package io.github.brushup.decksservice.dao;

import java.util.UUID;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Delete;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import com.datastax.oss.driver.shaded.guava.common.base.Optional;

import io.github.brushup.decksservice.entity.Card;

@Dao
public interface CardDao {
    
    @Select
    Optional<Card> findById(UUID id);

    @Insert
    void save(Card card);

    @Delete
    void delete(Card card);
}