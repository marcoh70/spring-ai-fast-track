package com.spring.ai;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OpenaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenaiApplication.class, args);
	}

	@Bean
	ApplicationRunner runner(OllamaChatModel model) {
		return args -> {
			System.out.println("-------------------");
			System.out.println(model);
			System.out.println("-------------------");
		};
	}

}
