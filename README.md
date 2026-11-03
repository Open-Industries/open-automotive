# Open Automotive — Reference Automotive Core Platform

A **standard, generic automotive core platform** that any organization in this vertical
can deploy and adapt: built as **microservices and serverless functions** on
**Java 21 + Quarkus**, aligned with **The Twelve-Factor App**, documented under the ISO
architecture and quality standards, and aligned with the domain standards of the
vertical (see the traceability matrix). Everything is *aligned to* the cited standards —
**no certification, conformance or regulatory-compliance claim is made**. All data is
**synthetic**.

> Reference implementation: small, complete and runnable. The rules that matter in this
> domain are enforced in code and covered by tests — start with the invariant:
> the available parts inventory of a plant never goes negative — a withdrawal that exceeds availability is rejected (422) and records nothing, so production planning never runs on phantom stock.

## Standards alignment

| Standard / framework | What it governs here | Where |
|---|---|---|
| ISO/IEC/IEEE **42010** | Architecture description | `docs/architecture/architecture-description.md` |
| ISO/IEC **25010** (SQuaRE) | Product quality model and scenarios | `docs/architecture/quality-attributes.md` |
| ISO/IEC/IEEE **15288 & 12207** | Life-cycle processes | `docs/lifecycle/software-lifecycle.md` |
| ISO/IEC **27001 & 27002** | Information security | `docs/security/` |
| **The Twelve-Factor App** | Cloud-native methodology | `docs/architecture/twelve-factor.md` |
| ISO 26262 | Functional safety (ASIL) | `docs/domain/domain-standards.md` mapping; safety rules context |
| ISO 21434 | Road-vehicle cybersecurity | Threat model + security architecture |
| ISO 21448 (SOTIF) | Safety of the intended functionality | Diagnostic scoring posture |
| Automotive SPICE | Process capability | Life-cycle gates in `docs/lifecycle/` |
| AUTOSAR | Software architecture (in-vehicle) | Boundary: platform is off-board; adapters at the edge |
| ISO 15118 | Vehicle-to-grid communication | Boundary: charging communication stays in the charger/vehicle |

Full traceability: `docs/compliance/standards-traceability.md`.

## The platform at a glance

### Microservices (Quarkus, `services/`)

| Service | Port | Alignment | Responsibility |
|---|---|---|---|
| `vehicle-service` | 8081 | ISO 26262 program data; VIN handling per privacy-by-design | Vehicle registry; the VIN is masked at registration and never kept in clear. |
| `parts-inventory-service` | 8082 | Production logistics integrity | Guarded parts availability per plant and the withdrawals against it. |
| `safety-rules-service` | 8083 | ISO 26262 envelope discipline (alignment, not assessment) | Per-system-area safety envelopes (coverage, defect rate) with reasoned decisions. |
| `model-catalog-service` | 8084 | Product definition data | Vehicle model catalogue: segments and capacities. |
| `notification-service` | 8085 | Platform support (shared pattern) | Channel-agnostic dispatch of domain notifications from events. |

### Serverless functions (Quarkus Funqy + Knative, `functions/`)

| Function | Trigger | Responsibility |
|---|---|---|
| `diagnostic-anomaly-scorer` | Event / HTTP | Scores vehicle diagnostic signals (vibration, thermal, error rate) into a transparent score with rationale. |
| `safety-case-evaluator` | Event / HTTP | Compares open safety findings and requirement coverage against program targets; one finding per breach. |

## Design principles

1. **One bounded context per service**, identical hexagonal layout everywhere.
2. **The domain invariant is code, not prose** — the available parts inventory of a plant never goes negative — a withdrawal that exceeds availability is rejected (422) and records nothing, so production planning never runs on phantom stock.
3. **Privacy by design** — Vehicle identifiers (VIN) are validated at registration and never stored or returned in clear — only a masked form is kept. All identifiers are synthetic.
4. **Database per service, API-first (OpenAPI), events as CloudEvents.**
5. **Adapt by configuration** — rules, thresholds, tariffs/rates and providers are data
   and configuration, not code forks (see *Adaptation guide*).

## Quick start

Prerequisites: Java 21, Maven 3.9+, optionally Docker/Podman and Knative/OpenShift.

```bash
mvn verify                                    # build and test everything
mvn -pl services/vehicle-service quarkus:dev
docker compose up -d                          # local PostgreSQL, Kafka, Keycloak
```

## Deployment

- **Kubernetes / OpenShift**: `kubectl apply -k deploy/kubernetes/`.
- **Serverless**: Knative Services in `deploy/knative/` (scale to zero).
- **Local**: `docker-compose.yml` for dependencies; services run with `quarkus:dev`.

## Adaptation guide

| You want to… | Change only… |
|---|---|
| Adjust domain rules and thresholds | Rule data in the `safety-rules-service` context and function configuration |
| Change the guarded capacity semantics | The `parts-inventory-service` context (invariant stays: never negative) |
| Plug your IdP / providers | OIDC at the gateway; provider adapters at the boundary |
| Replace in-memory persistence | The repository port's JPA adapter on the service's own database (`DB_KIND`, `DB_URL`) |

## Documentation map

- Architecture (42010): `docs/architecture/` · Decisions: `docs/adr/`
- Domain standards: `docs/domain/domain-standards.md`
- Quality (25010), Security (27001/27002), Life cycle (15288/12207), API, Data, Operations: under `docs/`

## License

Apache-2.0 — see `LICENSE`.
