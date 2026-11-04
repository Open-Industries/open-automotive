package org.openindustries.automotive.safety;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/** Outbound port; default in-memory adapter, production adapter is JPA on this service's own database. */
@ApplicationScoped
public class SafetyRuleRepository {
    private final ConcurrentHashMap<UUID, SafetyRule> store = new ConcurrentHashMap<>();

    public SafetyRule save(SafetyRule rule) {
        store.put(rule.id, rule);
        return rule;
    }

    public Optional<SafetyRule> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<SafetyRule> findAll() {
        return List.copyOf(store.values());
    }

    /** Active rules for a scope, highest priority (lowest number) first. */
    public List<SafetyRule> activeFor(String systemArea) {
        return store.values().stream()
                .filter(r -> r.systemArea.equals(systemArea) && "ACTIVE".equals(r.status))
                .sorted(Comparator.comparingInt(r -> r.priority))
                .toList();
    }
}
