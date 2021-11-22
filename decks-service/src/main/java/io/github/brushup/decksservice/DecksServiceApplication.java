package io.github.brushup.decksservice;

import java.nio.file.Path;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
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
    public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
        Path bundle = astraProperties.getSecureConnectBundle().toPath();
        return builder -> builder.withCloudSecureConnectBundle(bundle);
    }
		

}
