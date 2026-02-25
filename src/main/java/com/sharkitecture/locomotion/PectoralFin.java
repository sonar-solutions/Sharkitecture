package com.sharkitecture.locomotion;

import com.sharkitecture.skeleton.Cartilage;

/**
 * Pectoral fins act like airplane wings, generating lift to prevent
 * the shark from sinking (sharks lack a swim bladder). They also
 * enable steering and braking.
 */
public class PectoralFin {

    public enum Side {
        LEFT, RIGHT
    }

    private final Side side;
    private final double spanCm;
    private final Cartilage support;

    public PectoralFin(Side side, double spanCm) {
        this.side = side;
        this.spanCm = spanCm;
        this.support = new Cartilage("pectoral_fin_" + side.name().toLowerCase(), 0.75, 0.4);
    }

    public double generateLift(double swimSpeed) {
        double stiffness = support.calculateStiffness();
        return stiffness * spanCm * swimSpeed * 0.01;
    }

    public double steer(double angle) {
        return angle * (spanCm / 100.0);
    }

    public Side getSide() {
        return side;
    }

    public double getSpanCm() {
        return spanCm;
    }
}
