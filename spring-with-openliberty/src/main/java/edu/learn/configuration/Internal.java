package edu.learn.configuration;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Internal {
    @NotBlank
    private String hostname;
}
