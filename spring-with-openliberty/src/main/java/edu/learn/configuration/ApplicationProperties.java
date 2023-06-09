package edu.learn.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "application")
@Setter @Getter
public class ApplicationProperties {
    @NotBlank
    private String applicationName;
    private Internal internal = new Internal();

}
