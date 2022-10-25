package ru.fa.software.engineering.resources;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import ru.fa.software.engineering.utils.KeycloakAdminClient;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class CheckKeycloakWorkResourceTest {

    @Inject
    KeycloakAdminClient keycloakAdminClient;

    @Test
    public void testHelloWithAdminAuthorizedEndpoint() {

        given().auth()
                .oauth2(keycloakAdminClient.getAccessToken("admin", "admin"))
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hello from RESTEasy Reactive"));
    }

    @Test
    public void testHelloUnauthorizedEndpoint() {

        given()
                .when().get("/hello")
                .then()
                .statusCode(401);
    }

}