# Diagnostic Anomaly Scorer (serverless function)

Quarkus Funqy function on Knative (scale to zero). Scores vehicle diagnostic signals (vibration, thermal, error rate) into a transparent score with rationale.

Invoke: `POST /diagnosticScorer` with the event payload.
