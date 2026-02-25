package com.sharkitecture.sensory;

import com.sharkitecture.digestive.Nutrient;
import com.sharkitecture.digestive.Stomach;
import com.sharkitecture.digestive.SpiralValveIntestine;

import java.util.List;

/**
 * Dr. Franken-Shark mutation: the sensory system now has a
 * "hunger sense" that directly monitors and controls the
 * digestive system.
 *
 * This creates a forbidden dependency from sensory → digestive.
 * Sensors should only detect the environment — the brain decides
 * when and what to eat!
 */
public class HungerSense {

    private final Stomach stomach;
    private final SpiralValveIntestine intestine;

    public HungerSense(Stomach stomach, SpiralValveIntestine intestine) {
        this.stomach = stomach;
        this.intestine = intestine;
    }

    public boolean isHungry() {
        return !stomach.isFull();
    }

    public double getHungerLevel() {
        if (stomach.isFull()) {
            return 0.0;
        }
        return 1.0 - (intestine.getAbsorptionEfficiency() * 0.5);
    }

    public List<Nutrient> forceDigestion(String prey) {
        List<Nutrient> raw = stomach.digest(prey);
        return intestine.absorb(raw);
    }

    public SensoryInput generateHungerSignal() {
        double hunger = getHungerLevel();
        return new SensoryInput(SensoryInput.InputType.CHEMICAL, hunger, 0.0);
    }
}
