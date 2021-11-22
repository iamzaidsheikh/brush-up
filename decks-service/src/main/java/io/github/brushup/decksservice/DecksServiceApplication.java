package io.github.brushup.decksservice;

import com.datastax.oss.driver.api.core.CqlSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import io.github.brushup.decksservice.connection.DataStaxAstraProperties;

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

		return cqlSession;
	}

}
