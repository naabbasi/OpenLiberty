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
    @Path("/status")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String serverStatus(){
        return "Server response at " + Instant.now();
    }
}
