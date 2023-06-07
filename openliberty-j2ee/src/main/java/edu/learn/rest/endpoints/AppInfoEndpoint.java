package edu.learn.rest.endpoints;

import java.time.Instant;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RequestScoped
@Path("/app")
public class AppInfoEndpoint {
    private static final Instant CURRENT_DATE_TIME = Instant.now();

    @Path("/status")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public InnerAppInfoEndpoint serverStatus(){
        var innerAppInfoEndpoint = new InnerAppInfoEndpoint("Server is up and running", CURRENT_DATE_TIME);
        return innerAppInfoEndpoint;
    }

    /**
     * InnerAppInfoEndpoint
     */
    public record InnerAppInfoEndpoint(String message, Instant applicationUpDateTime) {
        
    }
}