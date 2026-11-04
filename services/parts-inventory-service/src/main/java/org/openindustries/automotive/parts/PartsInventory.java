package org.openindustries.automotive.parts;

import java.util.UUID;

/**
 * PartsInventory aggregate — the guarded resource account of this core: a balance/capacity that
 * consumption operations draw from. Core invariant (ADR-0004): it NEVER goes negative;
 * an operation that would overdraw it is rejected and records nothing.
 */
public class PartsInventory {
    public UUID id;
        public String plantCode;
    public long availableUnits;
    public String status;

    public static PartsInventory open(String plantCode, long availableUnits) {
        PartsInventory a = new PartsInventory();
        a.id = UUID.randomUUID();
        a.plantCode = plantCode;
        a.availableUnits = availableUnits;
        a.status = "ACTIVE";
        return a;
    }
}
