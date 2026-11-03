package org.openindustries.automotive.parts;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/** Outbound port; default in-memory adapter, production adapter is JPA on this service's own database. */
@ApplicationScoped
public class PartsRepository {
    private final ConcurrentHashMap<UUID, PartsInventory> accounts = new ConcurrentHashMap<>();
    private final CopyOnWriteArrayList<PartsWithdrawal> records = new CopyOnWriteArrayList<>();

    public PartsInventory saveAccount(PartsInventory account) {
        accounts.put(account.id, account);
        return account;
    }

    public Optional<PartsInventory> findAccount(UUID id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public PartsWithdrawal saveRecord(PartsWithdrawal record) {
        records.add(record);
        return record;
    }

    public List<PartsWithdrawal> recordsOf(UUID accountId) {
        return records.stream().filter(r -> r.accountId.equals(accountId)).toList();
    }
}
