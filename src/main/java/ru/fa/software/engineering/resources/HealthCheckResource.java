package ru.fa.software.engineering.resources;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/health")
public class HealthCheckResource {

    @GET
    public Response checkHealth() {
        return Response.ok().build();
    }
}
