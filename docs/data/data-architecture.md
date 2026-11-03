# Data Architecture

Data view (VP-5) of the Architecture Description.

## 1. Ownership

| Data | Owner (only writer) | Store (reference → production) |
|---|---|---|
| Vehicles records | vehicle-service | in-memory → PostgreSQL `vehicle` |
| Parts Inventory records | parts-inventory-service | in-memory → PostgreSQL `parts` |
| Safety Rules records | safety-rules-service | in-memory → PostgreSQL `safety` |
| Vehicle Models records | model-catalog-service | in-memory → PostgreSQL `model` |
| Notifications records | notification-service | in-memory → PostgreSQL `notification` |

In-memory adapters exist so the platform runs anywhere with zero setup; production
persistence is configuration (`DB_KIND`, `DB_URL`) plus the JPA adapter — the domain and
contracts never change (12-Factor IV).

## 2. Privacy and identifiers

Vehicle identifiers (VIN) are validated at registration and never stored or returned in clear — only a masked form is kept. All identifiers are synthetic. Internal keys are UUIDs. All values in this repository are synthetic.

## 3. Consistency, evidence, retention

Strong within a context; eventual across contexts via CloudEvents with idempotent
consumers. Consumption and decision records are append-only evidence; corrections are
compensating records, never edits. Retention per the adopter's regulatory schedule;
personal data erasable per data-subject rights while evidential records are retained in
pseudonymized form (UUIDs only). Backups per service, encrypted, restore tested
(ISO 27002 A.8.13).
