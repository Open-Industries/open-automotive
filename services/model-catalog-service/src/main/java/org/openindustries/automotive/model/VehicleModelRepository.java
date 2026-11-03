package org.openindustries.automotive.model;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/** Outbound port; default in-memory adapter, production adapter is JPA on this service's own database. */
@ApplicationScoped
public class VehicleModelRepository {
    private final ConcurrentHashMap<UUID, VehicleModel> store = new ConcurrentHashMap<>();

    public VehicleModel save(VehicleModel entity) {
        store.put(entity.id, entity);
        return entity;
    }

    public Optional<VehicleModel> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<VehicleModel> findAll() {
        return List.copyOf(store.values());
    }
}
