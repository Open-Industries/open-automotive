package org.openindustries.automotive.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record CreateVehicleModelRequest(
        @NotBlank String name,
        @NotBlank @Pattern(regexp = "COMPACT|SUV|TRUCK|VAN") String segment,
        @Positive int seats) {
}
