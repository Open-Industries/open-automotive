package org.openindustries.automotive.vehicle;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class VehicleResourceTest {

    @Test
    void createAndFetch() {
        String id = given().contentType("application/json")
                .body("{\"modelCode\":\"AX-200\",\"powertrain\":\"EV\",\"vin\":\"1HGCM82633A123456\"}")
                .when().post("/api/v1/vehicles")
                .then().statusCode(201)
                .body("status", is("REGISTERED"))
                .body("vinMasked", endsWith("3456"))
                .extract().path("id");

        given().when().get("/api/v1/vehicles/" + id)
                .then().statusCode(200).body("status", is("REGISTERED"));
    }

    @Test
    void transitionWithoutBody() {
        // Lifecycle transitions take no body and must not require a Content-Type (no 415).
        String id = given().contentType("application/json")
                .body("{\"modelCode\":\"AX-200\",\"powertrain\":\"EV\",\"vin\":\"1HGCM82633A123456\"}")
                .when().post("/api/v1/vehicles")
                .then().statusCode(201)
                .extract().path("id");

        given().when().post("/api/v1/vehicles/" + id + "/activate")
                .then().statusCode(200).body("status", is("ACTIVE"));
    }
}
