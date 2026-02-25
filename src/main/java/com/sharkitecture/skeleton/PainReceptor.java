package com.sharkitecture.skeleton;

import com.sharkitecture.brain.Brain;
import com.sharkitecture.brain.NeuralSignal;

/**
 * Dr. Franken-Shark mutation: the skeleton now has pain receptors
 * that communicate directly with the brain, bypassing the sensory
 * system entirely.
 *
 * This creates a forbidden dependency from skeleton → brain.
 * Structural tissue shouldn't have a direct line to the brain —
 * that's what the sensory system is for!
 */
public class PainReceptor {

    private final Brain brain;
    private final Cartilage monitoredRegion;
    private double painThreshold;

    public PainReceptor(Brain brain, Cartilage monitoredRegion, double painThreshold) {
        this.brain = brain;
        this.monitoredRegion = monitoredRegion;
        this.painThreshold = painThreshold;
    }

    public NeuralSignal detectDamage(double impactForce) {
        double stiffness = monitoredRegion.calculateStiffness();
        double stressLevel = impactForce / (stiffness * 1000);

        if (stressLevel > painThreshold) {
            NeuralSignal painSignal = new NeuralSignal(NeuralSignal.Command.BRAKE, stressLevel);
            return brain.processSensoryInput(java.util.List.of());
        }

        return new NeuralSignal(NeuralSignal.Command.IDLE, 0.0);
    }

    public boolean isRegionDamaged(double impactForce) {
        return !monitoredRegion.canSupportLoad(impactForce);
    }

    public double getPainThreshold() {
        return painThreshold;
    }
}
