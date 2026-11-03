# Safety Case Evaluator (serverless function)

Quarkus Funqy function on Knative (scale to zero). Compares open safety findings and requirement coverage against program targets; one finding per breach.

Invoke: `POST /safetyCaseEvaluator` with the KPI payload.
