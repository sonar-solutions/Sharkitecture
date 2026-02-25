package com.sharkitecture.locomotion;

import com.sharkitecture.skeleton.Cartilage;

/**
 * The iconic dorsal fin provides stability during swimming,
 * preventing the shark from rolling. It does not generate thrust.
 */
public class DorsalFin {

    private final double heightCm;
    private final Cartilage support;

    public DorsalFin(double heightCm) {
        this.heightCm = heightCm;
        this.support = new Cartilage("dorsal_fin", 0.8, 0.3);
    }

    public double calculateStabilization(double currentSpeed) {
        double stiffness = support.calculateStiffness();
        return stiffness * heightCm * Math.log1p(currentSpeed);
    }

    public double getHeightCm() {
        return heightCm;
    }
}
