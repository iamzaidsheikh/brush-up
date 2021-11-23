package io.github.brushup.decksservice.mapper;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.mapper.MapperBuilder;
import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

import io.github.brushup.decksservice.dao.CardDao;
import io.github.brushup.decksservice.dao.DeckDao;

@Mapper
public interface DeckMapper {

    @DaoFactory
    DeckDao deckDao();

    @DaoFactory
    CardDao cardDao();
    
    static MapperBuilder<DeckMapper> builder(CqlSession session) {
        return new DeckMapperBuilder(session);
    }
    
}

