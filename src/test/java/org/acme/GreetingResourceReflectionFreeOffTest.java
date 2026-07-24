package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.containsString;

@QuarkusTest
@TestProfile(GreetingResourceReflectionFreeOffTest.ReflectionFreeOffProfile.class)
class GreetingResourceReflectionFreeOffTest {

    public static class ReflectionFreeOffProfile implements QuarkusTestProfile {
        @Override
        public Map<String, String> getConfigOverrides() {
            return Map.of(
                "quarkus.rest.jackson.optimization.enable-reflection-free-serializers", "false"
            );
        }
    }

    @Test
    void testPasswordNotExposedInResponseWithReflectionFreeSerializers() {
        given()
            .when().get("/hello")
            .then()
            .statusCode(200)
            .body(not(containsString("password")))
            .body(Matchers.not(containsString("someOtherField")));

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

    @Test
    void jsonAlias() {
        given()
                .contentType("application/json")
                .body("""
                        {
                            "documentId": "123e4567-e89b-12d3-a456-426614174000"
                        }
                        """)
                .when().post("/hello/json-alias" )
                .then()
                .log().all()
                .statusCode(200)
                .body(containsString("123e4567-e89b-12d3-a456-426614174000"));

    }

}

