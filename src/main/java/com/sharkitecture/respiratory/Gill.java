package com.sharkitecture.respiratory;

/**
 * Sharks have 5 to 7 gill slits on each side of their head.
 * Many species must keep swimming to force water over the gills
 * (ram ventilation) — if they stop, they stop breathing.
 */
public class Gill {

    private final int slitCount;
    private final double surfaceAreaCm2;

    public Gill(int slitCount, double surfaceAreaCm2) {
        this.slitCount = slitCount;
        this.surfaceAreaCm2 = surfaceAreaCm2;
    }

    public GasExchange extractOxygen(double waterFlowRate, double waterOxygenContent) {
        double absorptionRate = surfaceAreaCm2 * 0.001 * waterFlowRate;
        double oxygenAbsorbed = absorptionRate * waterOxygenContent;
        double co2Expelled = oxygenAbsorbed * 0.85;

        return new GasExchange(oxygenAbsorbed, co2Expelled);
    }

    public double getMinimumSwimSpeed() {
        return 100.0 / surfaceAreaCm2;
    }

    public int getSlitCount() {
        return slitCount;
    }

    public double getSurfaceAreaCm2() {
        return surfaceAreaCm2;
    }
}
