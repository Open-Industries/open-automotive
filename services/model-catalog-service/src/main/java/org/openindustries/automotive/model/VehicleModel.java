package org.openindustries.automotive.model;

import java.util.UUID;

/** VehicleModel aggregate — Vehicle model catalogue: segments and capacities. */
public class VehicleModel {
    public UUID id;
        public String name;
        public String segment;
        public int seats;
    public String status;

    public static VehicleModel create(String name, String segment, int seats) {
        VehicleModel e = new VehicleModel();
        e.id = UUID.randomUUID();
        e.name = name;
        e.segment = segment;
        e.seats = seats;
        e.status = "DRAFT";
        return e;
    }
}
