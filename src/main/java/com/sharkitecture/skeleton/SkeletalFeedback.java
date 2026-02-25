package com.sharkitecture.skeleton;

import com.sharkitecture.locomotion.CaudalFin;
import com.sharkitecture.locomotion.DorsalFin;
import com.sharkitecture.locomotion.Muscle;
import com.sharkitecture.locomotion.PectoralFin;

/**
 * Dr. Franken-Shark mutation: the skeleton now monitors the fins
 * and muscles attached to it, creating a feedback loop.
 *
 * This creates a dependency from skeleton → locomotion.
 * Since locomotion already depends on skeleton (muscles attach to
 * cartilage), this forms a TANGLE.
 */
public class SkeletalFeedback {

    private final CaudalFin caudalFin;
    private final DorsalFin dorsalFin;
    private final PectoralFin leftPectoral;
    private final PectoralFin rightPectoral;

    public SkeletalFeedback(CaudalFin caudalFin, DorsalFin dorsalFin,
                            PectoralFin leftPectoral, PectoralFin rightPectoral) {
        this.caudalFin = caudalFin;
        this.dorsalFin = dorsalFin;
        this.leftPectoral = leftPectoral;
        this.rightPectoral = rightPectoral;
    }

    public double measureStructuralStress() {
        double tailStress = caudalFin.getMuscle().getFatigue();
        double maxSpeed = caudalFin.getMaxSpeed();
        return tailStress * maxSpeed;
    }

    public boolean isStructurallySound() {
        return measureStructuralStress() < 100.0;
    }

    public double calculateTotalFinSpan() {
        return caudalFin.getSpanCm()
                + dorsalFin.getHeightCm()
                + leftPectoral.getSpanCm()
                + rightPectoral.getSpanCm();
    }
}
