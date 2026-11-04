package org.openindustries.automotive.functions.diagnostics;

import io.quarkus.funqy.Funq;

/**
 * Serverless function (Knative / OpenShift Serverless).
 * Scores vehicle diagnostic signals (vibration, thermal, error rate) into a transparent score with rationale.
 * Transparent, auditable rules: NORMAL (< 35), WARNING (35-69), CRITICAL (>= 70);
 * the rationale is returned with the score for audit (ISO/IEC 27002 A.8.15).
 * Stateless by design (12-Factor VI): all state arrives in the request.
 */
public class Function {

    public record Event(String vehicleId, int vibrationIndex, int thermalIndex, int errorRatePct) {
    }

    public record Score(String vehicleId, int score, String decision, String rationale) {
    }

    @Funq
    public Score diagnosticScorer(Event event) {
        int score = 0;
        StringBuilder why = new StringBuilder();
        if (event.vibrationIndex() >= 70) {
            score += 40;
            why.append("vibrationIndex >= 70; ");
        }
        if (event.thermalIndex() >= 60) {
            score += 30;
            why.append("thermalIndex >= 60; ");
        }
        if (event.errorRatePct() >= 50) {
            score += 30;
            why.append("errorRatePct >= 50; ");
        }
        String decision = score >= 70 ? "CRITICAL" : score >= 35 ? "WARNING" : "NORMAL";
        return new Score(event.vehicleId(), score, decision,
                why.length() == 0 ? "no signals" : why.toString().trim());
    }
}
