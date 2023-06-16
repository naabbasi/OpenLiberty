package edu.learn;

import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NoInitialContextException;

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
			System.out.println(applicationProperties.getExternal().getPostgresDatabase().getUrl());

			try {
				InitialContext ctx = new InitialContext();
				NamingEnumeration<NameClassPair> list = ctx.list("jdbc");
				while (list.hasMore()) {
					NameClassPair nameClassPair = list.next();
					System.out.println(nameClassPair.getName());
					System.out.println("isRelative: " + nameClassPair.isRelative());
					System.out.println(nameClassPair);
				}
			} catch (NoInitialContextException nie) {
				nie.printStackTrace();
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringWithOpenlibertyApplication.class, args);
	}

}
