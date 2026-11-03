package org.openindustries.automotive.parts;

import java.time.Instant;
import java.util.UUID;

/** One guarded consumption record — the auditable evidence of a draw on a PartsInventory. */
public class PartsWithdrawal {
    public UUID id;
    public UUID accountId;
    public String partCategory;
    public long units;
    public long remainingAfter;
    public Instant recordedAt;

    public static PartsWithdrawal of(UUID accountId, String partCategory, long units, long remainingAfter) {
        PartsWithdrawal r = new PartsWithdrawal();
        r.id = UUID.randomUUID();
        r.accountId = accountId;
        r.partCategory = partCategory;
        r.units = units;
        r.remainingAfter = remainingAfter;
        r.recordedAt = Instant.now();
        return r;
    }
}
