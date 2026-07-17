package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

@QuarkusTest
class GreetingResourceTest {

    @Test
    void testPasswordNotExposedInResponse() {
        given()
            .when().get("/hello")
            .then()
            .statusCode(200)
            .body(containsString("password"))
            .body(not(containsString("someOtherField")));
    }


    @Test
    void jsonAnyGetter() {
        given()
                .when().get("/hello/json-any-getter")
                .then()
                .statusCode(200)
                .body(containsString("color"));
    }

    @Test
    void characterUnboxing() {
        given()
                .when().get("/hello/character-unboxing")
                .then()
                .statusCode(200);

    }
}