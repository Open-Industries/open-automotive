# Standards Traceability Matrix

Every standard or framework claimed by this platform and where its evidence lives.
"Aligned to" throughout — no certification or regulatory-compliance claim is made.

| Standard / framework | Area | Evidence |
|---|---|---|
| ISO/IEC/IEEE 42010 | Architecture description | `docs/architecture/architecture-description.md`; `docs/adr/` |
| ISO/IEC 25010 | Quality model | `docs/architecture/quality-attributes.md` (QS-01…QS-08); tests; SLOs |
| ISO/IEC/IEEE 15288 / 12207 | Life-cycle processes | `docs/lifecycle/software-lifecycle.md` |
| ISO/IEC 27001 / 27002 | ISMS controls | `docs/security/` |
| Twelve-Factor App | Cloud methodology | `docs/architecture/twelve-factor.md` |
| OpenAPI 3.1 / AsyncAPI 2.6 / CloudEvents 1.0 | Contracts & events | `contracts/` |
| RFC 9457 | Error format | `Problem.java` in every service |
| ISO 26262 | Functional safety (ASIL) | `docs/domain/domain-standards.md` mapping; safety rules context |
| ISO 21434 | Road-vehicle cybersecurity | Threat model + security architecture |
| ISO 21448 (SOTIF) | Safety of the intended functionality | Diagnostic scoring posture |
| Automotive SPICE | Process capability | Life-cycle gates in `docs/lifecycle/` |
| AUTOSAR | Software architecture (in-vehicle) | Boundary: platform is off-board; adapters at the edge |
| ISO 15118 | Vehicle-to-grid communication | Boundary: charging communication stays in the charger/vehicle |
