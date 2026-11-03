package org.openindustries.automotive.safety;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record CreateSafetyRuleRequest(
        @NotBlank String name,
        @NotBlank @Pattern(regexp = "BRAKING|STEERING|BATTERY|ADAS") String systemArea,
        @Positive long coveragePoints,
        @Positive int maxDefectPpm,
        @Min(1) @Max(100) int priority) {
}
