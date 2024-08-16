package dev.darsh.configserver2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class Configserver2Application {

	public static void main(String[] args) {
		SpringApplication.run(Configserver2Application.class, args);
	}

}
