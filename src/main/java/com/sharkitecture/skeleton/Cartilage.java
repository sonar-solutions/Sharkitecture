package com.sharkitecture.skeleton;

/**
 * Unlike bony fish, sharks have skeletons made entirely of cartilage.
 * Cartilage is lighter and more flexible than bone, giving sharks
 * superior agility and buoyancy.
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
