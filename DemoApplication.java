package com.app.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
@EnableAsync
public class DemoApplication {

	public static void main(String[] args) {
		try {
			Files.createDirectories(Paths.get("/static/"));
		} catch (IOException ex){
			ex.printStackTrace();
		}
		SpringApplication.run(DemoApplication.class, args);
	}

}
