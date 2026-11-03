# Parts Inventory Service

Bounded context: **Parts Inventory** — Guarded parts availability per plant and the withdrawals against it.

Alignment: Production logistics integrity. Core invariant, enforced in code and tests: **the guarded
balance/capacity never goes negative** — over-drawing operations are rejected with `422`
and nothing is recorded. All data is synthetic.

| Item | Value |
|---|---|
| Port | `8082` (`HTTP_PORT`) |
| Base path | `/api/v1/parts-inventory` |
| Owns data | PartsInventory accounts, PartsWithdrawal records |
| Publishes | `parts.consumed`, `parts.insufficient` |

Run locally: `mvn -pl services/parts-inventory-service quarkus:dev`.
