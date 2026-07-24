package org.acme;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.LinkedHashMap;
import java.util.Map;

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

    @GET
    @Path("raw-types")
    @Produces(MediaType.APPLICATION_JSON)
    public Map rawTypes() {
        return Map.of("key", "value");
    }

    @GET
    @Path("json-any-getter")
    @Produces(MediaType.APPLICATION_JSON)
    public Hero getHero() {
        Hero hero = new Hero();
        hero.name = "Batman";
        hero.additionalProperties = Map.of("color", "black", "parents", "dead");
        return hero;
    }

    @GET
    @Path("character-unboxing")
    @Produces(MediaType.APPLICATION_JSON)
    public Villain getVillain() {
        Villain villain = new Villain();
        villain.setGender(null);
        villain.setName("Joker");
        return villain;
    }

    @POST
    @Path("json-alias")
    @Produces(MediaType.APPLICATION_JSON)
    public Request getRequest(Request request) {
        return request;
    }


    public static class Villain {
        private String name;

        private Character gender;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Character getGender() {
            return gender;
        }

        public void setGender(Character gender) {
            this.gender = gender;
        }
    }


    public static class Hero {


        private String name;

        @JsonIgnore
        @JsonAnyGetter
        private Map<String, Object> additionalProperties = new LinkedHashMap<>();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Map<String, Object> getAdditionalProperties() {
            return additionalProperties;
        }

        public void setAdditionalProperties(Map<String, Object> additionalProperties) {
            this.additionalProperties = additionalProperties;
        }
    }
}
