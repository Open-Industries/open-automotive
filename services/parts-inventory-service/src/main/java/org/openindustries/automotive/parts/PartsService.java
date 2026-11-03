package org.openindustries.automotive.parts;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PartsService {

    @Inject
    PartsRepository repository;

    public PartsInventory open(OpenPartsInventoryRequest request) {
        return repository.saveAccount(PartsInventory.open(request.plantCode(), request.availableUnits()));
    }

    public PartsInventory get(UUID id) {
        return repository.findAccount(id)
                .orElseThrow(() -> new DomainException(404, "PartsInventory not found: " + id));
    }

    /**
     * Draws from the account. Invariant: the balance/capacity never goes negative —
     * an over-drawing operation is rejected (422) and nothing is recorded.
     */
    public PartsWithdrawal consume(UUID accountId, CreatePartsWithdrawalRequest request) {
        PartsInventory account = get(accountId);
        if (!"ACTIVE".equals(account.status)) {
            throw new DomainException(409, "PartsInventory is not ACTIVE");
        }
        if (request.units() > account.availableUnits) {
            throw new DomainException(422, "Insufficient capacity: requested " + request.units()
                    + " exceeds available " + account.availableUnits);
        }
        account.availableUnits -= request.units();
        repository.saveAccount(account);
        return repository.saveRecord(PartsWithdrawal.of(accountId, request.partCategory(), request.units(), account.availableUnits));
    }

    public List<PartsWithdrawal> recordsOf(UUID accountId) {
        get(accountId);
        return repository.recordsOf(accountId);
    }
}
