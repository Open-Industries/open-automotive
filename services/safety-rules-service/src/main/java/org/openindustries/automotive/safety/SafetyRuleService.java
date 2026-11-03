package org.openindustries.automotive.safety;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class SafetyRuleService {

    @Inject
    SafetyRuleRepository repository;

    public SafetyRule create(CreateSafetyRuleRequest request) {
        return repository.save(SafetyRule.create(request.name(), request.systemArea(),
                request.coveragePoints(), request.maxDefectPpm(), request.priority()));
    }

    public SafetyRule get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new DomainException(404, "SafetyRule not found: " + id));
    }

    public List<SafetyRule> list() {
        return repository.findAll();
    }

    public SafetyRule activate(UUID id) {
        SafetyRule rule = get(id);
        rule.status = "ACTIVE";
        return repository.save(rule);
    }

    public Evaluation evaluate(EvaluateRequest request) {
        var candidates = repository.activeFor(request.systemArea());
        if (candidates.isEmpty()) {
            return new Evaluation("DENIED", null,
                    "No active rule for " + request.systemArea());
        }
        for (SafetyRule rule : candidates) {
            if (rule.maxDefectPpm <= request.requiredMaxDefectPpm()
                    && rule.coveragePoints >= request.requiredCoveragePoints()) {
                return new Evaluation("ALLOWED", rule.id,
                        "Rule '" + rule.name + "' covers the request");
            }
        }
        return new Evaluation("DEGRADED", candidates.get(0).id,
                "Active rules exist for " + request.systemArea() + " but none meets the requirement");
    }
}
