package org.openindustries.automotive.vehicle;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class VehicleService {

    @Inject
    VehicleRepository repository;

    public Vehicle create(CreateVehicleRequest request) {
        return repository.save(Vehicle.create(request.modelCode(), request.powertrain(), request.vin()));
    }

    public Vehicle get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new DomainException(404, "Vehicle not found: " + id));
    }

    public List<Vehicle> list() {
        return repository.findAll();
    }

    public Vehicle activate(UUID id) {
        Vehicle entity = get(id);
        if ("RETIRED".equals(entity.status)) {
            throw new DomainException(409, "A retired vehicle is terminal");
        }
        if ("ACTIVE".equals(entity.status)) {
            throw new DomainException(409, "Vehicle already ACTIVE");
        }
        entity.status = "ACTIVE";
        return repository.save(entity);
    }

    public Vehicle retire(UUID id) {
        Vehicle entity = get(id);
        if ("RETIRED".equals(entity.status)) {
            throw new DomainException(409, "A retired vehicle is terminal");
        }
        if ("RETIRED".equals(entity.status)) {
            throw new DomainException(409, "Vehicle already RETIRED");
        }
        entity.status = "RETIRED";
        return repository.save(entity);
    }
}
