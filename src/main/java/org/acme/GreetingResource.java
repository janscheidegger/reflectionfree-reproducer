package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public GreetingWithUser hello() {
        GreetingWithUser greetingWithUser = new GreetingWithUser();
        greetingWithUser.message = "Hello from Quarkus REST";
        greetingWithUser.someOtherField = "Some other field";
        User user = new User();
        user.name = "John";
        user.password = "abcd";
        greetingWithUser.user = user;

        return greetingWithUser;

    }
}
