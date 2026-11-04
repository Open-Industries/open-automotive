# Model Catalog Service

Bounded context: **Vehicle Models** — Vehicle model catalogue: segments and capacities.

Alignment: Product definition data. Lifecycle transitions take no body. All data is synthetic.

| Item | Value |
|---|---|
| Port | `8084` (`HTTP_PORT`) |
| Base path | `/api/v1/models` |
| Owns data | VehicleModel records |

Run locally: `mvn -pl services/model-catalog-service quarkus:dev`.
