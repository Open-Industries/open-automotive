package org.openindustries.automotive.functions.safetycase;

import io.quarkus.funqy.Funq;

import java.util.ArrayList;
import java.util.List;

/**
 * Serverless function (Knative / OpenShift Serverless).
 * Compares open safety findings and requirement coverage against program targets; one finding per breach.
 * Compares measured values with targets and returns a verdict with one finding per
 * breach — machine-checkable assurance evidence.
 */
public class Function {

    public record Kpi(String vehicleId, int measuredOpenFindings, int targetOpenFindings,
                      double measuredRequirementCoveragePct, double targetRequirementCoveragePct) {
    }

    public record Verdict(String vehicleId, boolean compliant, List<String> findings) {
    }

    @Funq
    public Verdict safetyCaseEvaluator(Kpi kpi) {
        List<String> findings = new ArrayList<>();
        if (kpi.measuredOpenFindings() > kpi.targetOpenFindings()) {
            findings.add("openFindings " + kpi.measuredOpenFindings() + " exceeds target " + kpi.targetOpenFindings());
        }
        if (kpi.measuredRequirementCoveragePct() < kpi.targetRequirementCoveragePct()) {
            findings.add("requirementCoveragePct " + kpi.measuredRequirementCoveragePct() + " below target " + kpi.targetRequirementCoveragePct());
        }
        return new Verdict(kpi.vehicleId(), findings.isEmpty(), findings);
    }
}
