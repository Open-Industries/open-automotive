package org.openindustries.automotive.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class VehicleModelResourceTest {

    @Test
    void createAndTransition() {
        String id = given().contentType("application/json")
                .body("{\"name\":\"AX-200 Touring\",\"segment\":\"SUV\",\"seats\":5}")
                .when().post("/api/v1/models")
                .then().statusCode(201)
                .body("status", is("DRAFT"))
                .body("segment", is("SUV"))
                .extract().path("id");

        given().when().post("/api/v1/models/" + id + "/publish")
                .then().statusCode(200).body("status", is("PUBLISHED"));
    }
}
