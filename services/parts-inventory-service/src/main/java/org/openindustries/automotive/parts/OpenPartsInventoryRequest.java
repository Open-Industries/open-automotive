package org.openindustries.automotive.parts;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record OpenPartsInventoryRequest(
        @NotBlank String plantCode,
        @PositiveOrZero long availableUnits) {
}
