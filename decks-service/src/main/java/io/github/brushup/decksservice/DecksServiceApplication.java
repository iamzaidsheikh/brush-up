package io.github.brushup.decksservice;

import java.io.FileNotFoundException;

import com.datastax.oss.driver.api.core.CqlSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import io.github.brushup.decksservice.connection.DataStaxAstraProperties;
import io.github.brushup.decksservice.dao.CardDao;
import io.github.brushup.decksservice.dao.DeckDao;
import io.github.brushup.decksservice.mapper.DeckMapper;
import io.github.brushup.decksservice.mapper.DeckMapperBuilder;
import io.github.brushup.decksservice.utils.CqlFileUtils;

@SpringBootApplication
@EnableConfigurationProperties(DataStaxAstraProperties.class)
public class DecksServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DecksServiceApplication.class, args);
	}

	@Bean
	public CqlSession connectToAstra(DataStaxAstraProperties astraProperties) {

		CqlSession cqlSession = CqlSession.builder()
				.withCloudSecureConnectBundle(astraProperties.getSecureConnectBundle().toPath())
				.withAuthCredentials(astraProperties.getUsername(),
						astraProperties.getPassword())
				.withKeyspace(astraProperties.getKeyspace())
				.build();

		createSchemaIfNeeded(cqlSession);		

		return cqlSession;
	}

	public void createSchemaIfNeeded(CqlSession cqlSession) {
        try {
            CqlFileUtils.executeCQLFile(cqlSession, "schema.cql");
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

	@Bean
	public DeckMapper getMapper(CqlSession session) {
		return new DeckMapperBuilder(session).build();
	}

	@Bean
	public DeckDao getDeckDao(DeckMapper deckMapper) {
		return deckMapper.deckDao();
	}

	@Bean
	public CardDao getCardDao(DeckMapper deckMapper) {
		return deckMapper.cardDao();
	}

}
