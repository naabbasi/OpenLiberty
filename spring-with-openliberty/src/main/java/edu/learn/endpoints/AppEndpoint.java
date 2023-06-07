package edu.learn.endpoints;

import java.time.Instant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Working on OpenLiberty by
 * http://localhost:9080/spring-with-openliberty/app/status
 */
@RestController
@RequestMapping("/app")
public class AppEndpoint {
    private static final Instant SERVER_START_DATE_TIME = Instant.now();
    @GetMapping("/status")
    public InnerAppEndpoint serverStatus() {
        var innerAppEndpoint = new InnerAppEndpoint("Server up and running since ", SERVER_START_DATE_TIME);
        return innerAppEndpoint;
    }

    /**
     * InnerAppEndpoint
     * String message, Instant serverStartDateTime
     */
    record InnerAppEndpoint(String message, Instant serverStartDateTime) {
    }
}
