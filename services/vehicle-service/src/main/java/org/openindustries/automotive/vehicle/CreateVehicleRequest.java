package org.openindustries.automotive.vehicle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record CreateVehicleRequest(
        @NotBlank String modelCode,
        @NotBlank @Pattern(regexp = "EV|HYBRID|ICE|FCEV") String powertrain,
        @NotBlank String vin) {
}
