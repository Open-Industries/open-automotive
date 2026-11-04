package org.openindustries.automotive.parts;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class PartsResourceTest {

    @Test
    void consumptionDrawsDown() {
        String id = given().contentType("application/json")
                .body("{\"plantCode\":\"PLANT-01\",\"availableUnits\":500}")
                .when().post("/api/v1/parts-inventory")
                .then().statusCode(201).body("availableUnits", is(500))
                .extract().path("id");

        given().contentType("application/json")
                .body("{\"partCategory\":\"BATTERY\",\"units\":50}")
                .when().post("/api/v1/parts-inventory/" + id + "/withdrawals")
                .then().statusCode(200)
                .body("remainingAfter", is(450));
    }

    @Test
    void overdrawIsRejected() {
        String id = given().contentType("application/json")
                .body("{\"plantCode\":\"PLANT-01\",\"availableUnits\":500}")
                .when().post("/api/v1/parts-inventory")
                .then().statusCode(201)
                .extract().path("id");

        given().contentType("application/json")
                .body("{\"partCategory\":\"BATTERY\",\"units\":900}")
                .when().post("/api/v1/parts-inventory/" + id + "/withdrawals")
                .then().statusCode(422).body("status", is(422));

        given().when().get("/api/v1/parts-inventory/" + id)
                .then().statusCode(200).body("availableUnits", is(500));
    }
}
