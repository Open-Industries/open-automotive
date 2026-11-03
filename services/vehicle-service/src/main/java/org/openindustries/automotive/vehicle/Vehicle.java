package org.openindustries.automotive.vehicle;

import java.time.Instant;
import java.util.UUID;

/**
 * Vehicle aggregate — Vehicle registry; the VIN is masked at registration and never kept in clear.
 *
 * Privacy by design (ISO/IEC 27002 A.5.34): secret identifiers are validated at
 * creation and NEVER persisted or returned in clear — only a masked form is kept.
 * All identifiers in this reference implementation are synthetic.
 */
public class Vehicle {
    public UUID id;
        public String modelCode;
        public String powertrain;
        public String vinMasked;
    public String status;
    public Instant createdAt;

    public static Vehicle create(String modelCode, String powertrain, String vin) {
        Vehicle e = new Vehicle();
        e.id = UUID.randomUUID();
        e.modelCode = modelCode;
        e.powertrain = powertrain;
        e.vinMasked = mask(vin);
        e.status = "REGISTERED";
        e.createdAt = Instant.now();
        return e;
    }

    static String mask(String value) {
        if (value == null || value.length() < 4) {
            return "****";
        }
        return "*".repeat(value.length() - 4) + value.substring(value.length() - 4);
    }
}
