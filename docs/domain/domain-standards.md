# Domain Standards — Automotive

Alignment mapping for this core. *Aligned to* throughout: no certification, ASIL
assessment, or homologation claim is made.

## Concept mapping

| Platform concept | Domain standard | Mapping |
|---|---|---|
| Safety rules & decisions | ISO 26262 (ASIL-oriented envelopes) | Rules context: per-system-area coverage/defect envelopes with reasoned decisions |
| Threat model | ISO 21434 (TARA posture) | `docs/security/threat-model.md` follows the same asset/threat discipline |
| Diagnostic scoring | ISO 21448 (SOTIF) posture | Signal-based scoring for unknown-unsafe-scenario hunting support |
| Process gates | Automotive SPICE | Life-cycle gates G0–G5 mirror ASPICE-style evidence discipline |
| Vehicle software | AUTOSAR | Out of scope in-vehicle; this platform is the off-board/enterprise side |
| Charging communication | ISO 15118 | Out of scope; chargers and vehicles own that protocol |

## Scope boundaries

This core never performs safety functions in the vehicle and never replaces an ASIL
assessment, a TARA, or type approval. It manages enterprise data — vehicles, parts,
rules and evidence — around certified vehicle systems.
