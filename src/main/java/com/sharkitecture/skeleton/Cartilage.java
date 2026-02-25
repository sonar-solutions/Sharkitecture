package com.sharkitecture.skeleton;

import com.sharkitecture.locomotion.Muscle;

/**
 * Unlike bony fish, sharks have skeletons made entirely of cartilage.
 * Cartilage is lighter and more flexible than bone, giving sharks
 * superior agility and buoyancy.
 *
 * Dr. Franken-Shark modification: cartilage now knows about the
 * muscles attached to it, creating a dependency from skeleton → locomotion.
 * Since locomotion already depends on skeleton, this forms a TANGLE.
 */
public class Cartilage {

    private final String region;
    private final double density;
    private final double flexibility;

    public Cartilage(String region, double density, double flexibility) {
        this.region = region;
        this.density = density;
        this.flexibility = flexibility;
    }

    public double calculateStiffness() {
        return density * (1.0 - flexibility);
    }

    public boolean canSupportLoad(double loadNewtons) {
        return calculateStiffness() * 1000 >= loadNewtons;
    }

    /**
     * Creates a muscle attached to this cartilage region.
     * This creates a dependency from skeleton → locomotion,
     * forming a tangle with the existing locomotion → skeleton dependency.
     */
    public Muscle getAttachedMuscle() {
        return new Muscle(Muscle.FiberType.RED_SLOW_TWITCH, 1.0, this);
    }

    public String getRegion() {
        return region;
    }

    public double getDensity() {
        return density;
    }

    public double getFlexibility() {
        return flexibility;
    }
}
