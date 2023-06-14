package edu.learn.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import edu.learn.utils.DatabaseConnectionInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@ConfigurationProperties(prefix = "application")
@Setter
@Getter
@ToString
public class ApplicationProperties {
    @NotBlank
    private String applicationName;
    @Valid
    private Internal internal = new Internal();
    @Valid
    private External external = new External();

    @NoArgsConstructor
    @Setter
    @Getter
    public class Internal {
        @NotBlank
        private String hostname;

        public String getHostname() {
            return this.hostname;
        }
    }

    @NoArgsConstructor
    @Setter
    @Getter
    @ToString
    public class External {
        @NotBlank
        private String hostname;
        private DatabaseConnectionInfo postgresDatabase = new DatabaseConnectionInfo();
    }

}
