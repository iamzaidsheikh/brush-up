package io.github.brushup.decksservice.connection;

import java.io.File;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "astra")
@Getter
@Setter
public class DataStaxAstraProperties {
	
	private String username;
	private String password;
	private String keyspace;
    private File secureConnectBundle;
}
