package io.github.brushup.cardsservice;

import com.datastax.oss.driver.api.core.CqlSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import io.github.brushup.cardsservice.connection.DataStaxAstraProperties;
import io.github.brushup.cardsservice.dao.CardDao;
import io.github.brushup.cardsservice.mapper.CardMapper;
import io.github.brushup.cardsservice.mapper.CardMapperBuilder;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigurationProperties(DataStaxAstraProperties.class)
public class CardsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsServiceApplication.class, args);
	}

	@Bean
	public CqlSession connectToAstra(DataStaxAstraProperties astraProperties) {

		CqlSession cqlSession = CqlSession.builder()
				.withCloudSecureConnectBundle(astraProperties.getSecureConnectBundle().toPath())
				.withAuthCredentials(astraProperties.getUsername(),
						astraProperties.getPassword())
				.withKeyspace(astraProperties.getKeyspace())
				.build();

		return cqlSession;
	}

	@Bean
	public CardMapper getMapper(CqlSession session) {
		return new CardMapperBuilder(session).build();
	}

	@Bean
	public CardDao getCardDao(CardMapper cardMapper) {
		return cardMapper.cardDao();
	}

}
