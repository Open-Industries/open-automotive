package org.openindustries.automotive.safety;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class SafetyRuleResourceTest {

    @Test
    void activateThenEvaluateAllowed() {
        String id = given().contentType("application/json")
                .body("{\"name\":\"braking-asil-d\",\"systemArea\":\"BRAKING\",\"coveragePoints\":1000,\"maxDefectPpm\":10,\"priority\":1}")
                .when().post("/api/v1/safety-rules")
                .then().statusCode(201).body("status", is("DRAFT"))
                .extract().path("id");

        given().when().post("/api/v1/safety-rules/" + id + "/activate")
                .then().statusCode(200).body("status", is("ACTIVE"));

        given().contentType("application/json")
                .body("{\"systemArea\":\"BRAKING\",\"requiredMaxDefectPpm\":20,\"requiredCoveragePoints\":800}")
                .when().post("/api/v1/safety-rules/evaluate")
                .then().statusCode(200).body("decision", is("ALLOWED"));
    }

    @Test
    void evaluateWithoutRuleIsDenied() {
        given().contentType("application/json")
                .body("{\"systemArea\":\"STEERING\",\"requiredMaxDefectPpm\":20,\"requiredCoveragePoints\":800}")
                .when().post("/api/v1/safety-rules/evaluate")
                .then().statusCode(200).body("decision", is("DENIED"));
    }
}
