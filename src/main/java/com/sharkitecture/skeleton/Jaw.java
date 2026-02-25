package com.sharkitecture.skeleton;

/**
 * The shark jaw is not fused to the skull, allowing it to protrude
 * forward during a bite — generating tremendous force.
 * Great whites can exert over 4,000 PSI of bite force.
 */
public class Jaw {

    private final int toothRows;
    private final double biteForceNewtons;
    private final Cartilage upperJaw;
    private final Cartilage lowerJaw;

    public Jaw(int toothRows, double biteForceNewtons) {
        this.toothRows = toothRows;
        this.biteForceNewtons = biteForceNewtons;
        this.upperJaw = new Cartilage("upper_jaw", 0.9, 0.2);
        this.lowerJaw = new Cartilage("lower_jaw", 0.9, 0.15);
    }

    public double bite() {
        double structuralIntegrity = (upperJaw.calculateStiffness() + lowerJaw.calculateStiffness()) / 2;
        return biteForceNewtons * structuralIntegrity;
    }

    public int getActiveTeeth() {
        return toothRows * 5;
    }

    public int getToothRows() {
        return toothRows;
    }

    public double getBiteForceNewtons() {
        return biteForceNewtons;
    }
}
