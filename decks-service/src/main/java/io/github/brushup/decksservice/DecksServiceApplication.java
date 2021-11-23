package io.github.brushup.decksservice;

import java.io.FileNotFoundException;

import com.datastax.oss.driver.api.core.CqlSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import io.github.brushup.decksservice.connection.DataStaxAstraProperties;
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

	// @Bean
	// CommandLineRunner run(DeckMapper deckMapper) {
	// 	return args -> {
			
	// 		Card card = new Card();
	// 		card.setId(UUID.randomUUID());
	// 		card.setFront("This text is in front");
	// 		card.setBack("This text in the back");

	// 		CardDao cardDao = deckMapper.cardDao();
	// 		cardDao.save(card);

	// 		Deck deck = new Deck();
	// 		deck.setId(UUID.randomUUID());
	// 		deck.setNumCards(1);
	// 		List<Card> cards = new ArrayList<>();
	// 		cards.add(card);
	// 		deck.setCards(cards);

	// 		DeckDao deckDao = deckMapper.deckDao();
	// 		deckDao.save(deck);
	// 	};
	// }
}
