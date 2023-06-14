package edu.learn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import edu.learn.configuration.ApplicationProperties;

@SpringBootApplication(scanBasePackages = {"edu.learn"})
public class SpringWithOpenlibertyApplication {

	@Bean
	CommandLineRunner init(ApplicationProperties applicationProperties) {
		return args -> {
			System.out.println(applicationProperties.getApplicationName());
			System.out.println(applicationProperties.getInternal().getHostname());
			System.out.println(applicationProperties.getExternal().getPostgresDatabase().getUrl());
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringWithOpenlibertyApplication.class, args);
	}

}
