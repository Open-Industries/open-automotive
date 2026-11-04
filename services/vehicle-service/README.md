# Vehicle Service

Bounded context: **Vehicles** — Vehicle registry; the VIN is masked at registration and never kept in clear.

Alignment: ISO 26262 program data; VIN handling per privacy-by-design. Lifecycle transitions take no body; secret identifiers,
where the domain has them, are masked at creation and never stored or returned in clear.
All data is synthetic.

| Item | Value |
|---|---|
| Port | `8081` (`HTTP_PORT`) |
| Base path | `/api/v1/vehicles` |
| Owns data | Vehicle records |
| Publishes | `vehicle.created`, lifecycle events (see `contracts/asyncapi.yaml`) |

Run locally: `mvn -pl services/vehicle-service quarkus:dev`.
