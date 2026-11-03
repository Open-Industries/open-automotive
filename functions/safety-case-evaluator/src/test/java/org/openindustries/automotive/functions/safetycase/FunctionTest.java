package org.openindustries.automotive.functions.safetycase;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class FunctionTest {

    @Test
    void compliant() {
        given().contentType("application/json")
                .body("{\"vehicleId\":\"v-1\",\"measuredOpenFindings\":0,\"targetOpenFindings\":0,\"measuredRequirementCoveragePct\":100.0,\"targetRequirementCoveragePct\":100.0}")
                .when().post("/safetyCaseEvaluator")
                .then().statusCode(200).body("compliant", is(true)).body("findings.size()", is(0));
    }

    @Test
    void breachIsReported() {
        given().contentType("application/json")
                .body("{\"vehicleId\":\"v-2\",\"measuredOpenFindings\":3,\"targetOpenFindings\":0,\"measuredRequirementCoveragePct\":100.0,\"targetRequirementCoveragePct\":100.0}")
                .when().post("/safetyCaseEvaluator")
                .then().statusCode(200).body("compliant", is(false)).body("findings.size()", is(1));
    }
}
