package org.openindustries.automotive.model;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class VehicleModelService {

    @Inject
    VehicleModelRepository repository;

    public VehicleModel create(CreateVehicleModelRequest request) {
        return repository.save(VehicleModel.create(request.name(), request.segment(), request.seats()));
    }

    public VehicleModel get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new DomainException(404, "VehicleModel not found: " + id));
    }

    public List<VehicleModel> list() {
        return repository.findAll();
    }

    public VehicleModel publish(UUID id) {
        VehicleModel entity = get(id);
        if (!"DRAFT".equals(entity.status)) {
            throw new DomainException(409, "Only a DRAFT vehiclemodel can transition via publish");
        }
        entity.status = "PUBLISHED";
        return repository.save(entity);
    }
}
