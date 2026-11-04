# Safety Rules Service

Bounded context: **Safety Rules** — Per-system-area safety envelopes (coverage, defect rate) with reasoned decisions.

Alignment: ISO 26262 envelope discipline (alignment, not assessment). Decisions are `ALLOWED` / `DEGRADED` / `DENIED`, always
with the matched rule and a human-readable reason for audit. All data is synthetic.

| Item | Value |
|---|---|
| Port | `8083` (`HTTP_PORT`) |
| Base path | `/api/v1/safety-rules` |
| Owns data | SafetyRule rules |
| Publishes | `safety.activated` |

Run locally: `mvn -pl services/safety-rules-service quarkus:dev`.
