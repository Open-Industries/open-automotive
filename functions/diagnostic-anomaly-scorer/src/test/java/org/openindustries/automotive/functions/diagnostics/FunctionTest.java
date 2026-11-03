package org.openindustries.automotive.functions.diagnostics;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class FunctionTest {

    @Test
    void healthyIsNormal() {
        given().contentType("application/json")
                .body("{\"vehicleId\":\"v-1\",\"vibrationIndex\":5,\"thermalIndex\":10,\"errorRatePct\":2}")
                .when().post("/diagnosticScorer")
                .then().statusCode(200).body("decision", is("NORMAL")).body("score", is(0));
    }

    @Test
    void stackedSignalsAreCritical() {
        given().contentType("application/json")
                .body("{\"vehicleId\":\"v-2\",\"vibrationIndex\":90,\"thermalIndex\":80,\"errorRatePct\":60}")
                .when().post("/diagnosticScorer")
                .then().statusCode(200).body("decision", is("CRITICAL")).body("score", is(100));
    }
}
