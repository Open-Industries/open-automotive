package org.openindustries.automotive.vehicle;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@Path("/api/v1/vehicles")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Vehicles", description = "Vehicle registry; the VIN is masked at registration and never kept in clear.")
public class VehicleResource {

    @Inject
    VehicleService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a vehicle")
    public Response create(@Valid CreateVehicleRequest request) {
        return Response.status(Response.Status.CREATED).entity(service.create(request)).build();
    }

    @GET
    @Operation(summary = "List all")
    public List<Vehicle> list() {
        return service.list();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get one by id")
    public Vehicle get(@PathParam("id") UUID id) {
        return service.get(id);
    }

    @POST
    @Path("/{id}/activate")
    @Operation(summary = "Activate this vehicle (lifecycle transition, no body)")
    public Vehicle activate(@PathParam("id") UUID id) {
        return service.activate(id);
    }

    @POST
    @Path("/{id}/retire")
    @Operation(summary = "Retire this vehicle (lifecycle transition, no body)")
    public Vehicle retire(@PathParam("id") UUID id) {
        return service.retire(id);
    }
}
