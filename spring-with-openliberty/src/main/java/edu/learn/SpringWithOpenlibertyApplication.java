package edu.learn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.learn.configuration.ApplicationProperties;

@SpringBootApplication
public class SpringWithOpenlibertyApplication {

	@Bean
	CommandLineRunner init(ApplicationProperties applicationProperties) {
		return args -> {
			System.out.println(applicationProperties.getApplicationName());
			System.out.println(applicationProperties.getInternal().getHostname());
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringWithOpenlibertyApplication.class, args);
	}

}
