# Services

One independently deployable Quarkus microservice per bounded context. Every service
follows the same hexagonal layout, exposes `/openapi`, `/q/health` and `/q/metrics`,
returns RFC 9457 problem details, and is configured only through environment variables
(Twelve-Factor). Lifecycle transitions take no body; `@Consumes` appears only on
endpoints that read one.

**Implementation order** (see `../docs/implementation-roadmap.md`):
Vehicles → Parts Inventory → Safety Rules → Open APIs.

| Service | Port | Alignment |
|---|---|---|
| `vehicle-service` | 8081 | ISO 26262 program data; VIN handling per privacy-by-design |
| `parts-inventory-service` | 8082 | Production logistics integrity |
| `safety-rules-service` | 8083 | ISO 26262 envelope discipline (alignment, not assessment) |
| `model-catalog-service` | 8084 | Product definition data |
| `notification-service` | 8085 | Platform support (shared pattern) |

Run the pilot service:

```bash
mvn -pl services/vehicle-service quarkus:dev
# OpenAPI UI: http://localhost:8081/docs — health: http://localhost:8081/q/health
```
