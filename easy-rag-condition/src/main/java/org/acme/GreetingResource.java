package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class GreetingResource {

    @Inject
    MyAiService myAiService;

    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }
    @GET
    @Path("joke")
    @Produces(MediaType.TEXT_PLAIN)
    public String joke() {
        return myAiService.joke();
    }


    @GET
    @Path("poem")
    @Produces(MediaType.TEXT_PLAIN)
    public String poem() {
        return myAiService.writeAPoem("Flower", 4);
    }
}
