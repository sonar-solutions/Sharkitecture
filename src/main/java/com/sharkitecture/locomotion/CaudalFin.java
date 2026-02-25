package com.sharkitecture.locomotion;

import com.sharkitecture.skeleton.Cartilage;

/**
 * The caudal (tail) fin is the primary propulsion mechanism.
 * In great whites, the upper and lower lobes are nearly symmetrical,
 * enabling powerful, efficient swimming. The heterocercal shape
 * generates both thrust and lift.
 */
public class CaudalFin {

    private final double spanCm;
    private final Muscle muscle;

    public CaudalFin(double spanCm) {
        this.spanCm = spanCm;
        Cartilage caudalCartilage = new Cartilage("caudal_fin", 0.7, 0.6);
        this.muscle = new Muscle(Muscle.FiberType.WHITE_FAST_TWITCH, 15.0, caudalCartilage);
    }

    public double generateThrust(double tailBeatIntensity) {
        double muscleForce = muscle.contract(tailBeatIntensity);
        return muscleForce * (spanCm / 100.0);
    }

    public double getMaxSpeed() {
        return spanCm * 0.15;
    }

    public double getSpanCm() {
        return spanCm;
    }

    public Muscle getMuscle() {
        return muscle;
    }
}
