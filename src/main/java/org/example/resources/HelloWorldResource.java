package org.example.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/helloworld")
public class HelloWorldResource {

    @GET
    public String getHelloWorld() {
        return "Hallo!";
    }

}
