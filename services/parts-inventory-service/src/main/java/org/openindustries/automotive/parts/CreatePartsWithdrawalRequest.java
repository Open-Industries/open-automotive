package org.openindustries.automotive.parts;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record CreatePartsWithdrawalRequest(
        @NotBlank @Pattern(regexp = "TIRES|BRAKES|BATTERY|FILTERS") String partCategory,
        @Positive long units) {
}
