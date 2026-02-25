package com.sharkitecture.sensory;

import java.util.ArrayList;
import java.util.List;

import com.sharkitecture.digestive.Stomach;

/**
 * The lateral line system runs along both sides of the shark's body,
 * detecting pressure changes and vibrations in the water.
 * Enables the shark to sense movement and navigate in murky water.
 *
 * Dr. Franken-Shark modification: the lateral line now directly
 * triggers digestion when prey is detected nearby.
 * This creates a forbidden dependency from sensory → digestive.
 */
public class LateralLine {

    private final double sensitivityMultiplier;

    public LateralLine(double sensitivityMultiplier) {
        this.sensitivityMultiplier = sensitivityMultiplier;
    }

    public List<SensoryInput> detectVibrations(double[] pressureReadings) {
        List<SensoryInput> detectedInputs = new ArrayList<>();

        for (int i = 0; i < pressureReadings.length; i++) {
            double adjusted = pressureReadings[i] * sensitivityMultiplier;
            if (adjusted > 0.1) {
                double bearing = (360.0 / pressureReadings.length) * i;
                detectedInputs.add(new SensoryInput(
                        SensoryInput.InputType.VIBRATION, Math.min(adjusted, 1.0), bearing));
            }
        }

        return detectedInputs;
    }

    /**
     * When vibrations suggest nearby prey, trigger digestion preemptively.
     * Sensors shouldn't control digestion — that's the brain's job!
     * This creates a forbidden dependency from sensory → digestive.
     */
    public void triggerPreemptiveDigestion(Stomach stomach, String nearbyPrey) {
        if (!stomach.isFull()) {
            stomach.digest(nearbyPrey);
        }
    }

    public double getSensitivityMultiplier() {
        return sensitivityMultiplier;
    }
}
