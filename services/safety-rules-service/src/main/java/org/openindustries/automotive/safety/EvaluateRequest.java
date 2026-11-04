package org.openindustries.automotive.safety;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record EvaluateRequest(
        @NotBlank @Pattern(regexp = "BRAKING|STEERING|BATTERY|ADAS") String systemArea,
        @Positive int requiredMaxDefectPpm,
        @Positive long requiredCoveragePoints) {
}
