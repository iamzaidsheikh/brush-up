package io.github.brushup.savedcardsservice.mapper;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.mapper.MapperBuilder;
import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

import io.github.brushup.savedcardsservice.dao.SavedCardsDao;

@Mapper
public interface SavedCardsMapper {

    @DaoFactory
    SavedCardsDao savedCardsDao();
    
    static MapperBuilder<SavedCardsMapper> builder(CqlSession session) {
        return new SavedCardsMapperBuilder(session);
    }
    
}
