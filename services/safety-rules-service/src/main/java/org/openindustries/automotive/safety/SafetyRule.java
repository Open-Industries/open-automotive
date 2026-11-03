package org.openindustries.automotive.safety;

import java.util.UUID;

/**
 * SafetyRule aggregate — a decision rule of this core: for one systemArea, it guarantees a
 * capacity (coveragePoints) and a threshold (maxDefectPpm), with a precedence priority.
 * Only ACTIVE rules decide; every decision names its rule and its reason (audit).
 */
public class SafetyRule {
    public UUID id;
    public String name;
    public String systemArea;
    public long coveragePoints;
    public int maxDefectPpm;
    public int priority;
    public String status;

    public static SafetyRule create(String name, String systemArea, long coveragePoints, int maxDefectPpm, int priority) {
        SafetyRule r = new SafetyRule();
        r.id = UUID.randomUUID();
        r.name = name;
        r.systemArea = systemArea;
        r.coveragePoints = coveragePoints;
        r.maxDefectPpm = maxDefectPpm;
        r.priority = priority;
        r.status = "DRAFT";
        return r;
    }
}
