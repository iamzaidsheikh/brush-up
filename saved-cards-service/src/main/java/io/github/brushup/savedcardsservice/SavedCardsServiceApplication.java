package io.github.brushup.savedcardsservice;

import com.datastax.oss.driver.api.core.CqlSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import io.github.brushup.savedcardsservice.connection.DataStaxAstraProperties;

@SpringBootApplication
//@EnableEurekaClient
@EnableConfigurationProperties(DataStaxAstraProperties.class)
public class SavedCardsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SavedCardsServiceApplication.class, args);
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

}
