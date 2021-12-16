package io.github.brushup.cardsservice.mapper;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.mapper.MapperBuilder;
import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

import io.github.brushup.cardsservice.dao.CardDao;

@Mapper
public interface CardMapper {

    @DaoFactory
    CardDao cardDao();
    
    static MapperBuilder<CardMapper> builder(CqlSession session) {
        return new CardMapperBuilder(session);
    }
    
}
