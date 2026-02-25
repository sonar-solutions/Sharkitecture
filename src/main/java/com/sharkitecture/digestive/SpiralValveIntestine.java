package com.sharkitecture.digestive;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Sharks have a unique spiral valve intestine that increases
 * surface area for nutrient absorption without increasing length.
 * This corkscrew-shaped structure is found in no other animal group.
 */
public class SpiralValveIntestine {

    private final int spiralTurns;
    private final double absorptionEfficiency;

    public SpiralValveIntestine(int spiralTurns) {
        this.spiralTurns = spiralTurns;
        this.absorptionEfficiency = Math.min(0.95, 0.5 + (spiralTurns * 0.05));
    }

    public List<Nutrient> absorb(List<Nutrient> digestedNutrients) {
        return digestedNutrients.stream()
                .map(n -> new Nutrient(n.getType(), n.getAmount() * absorptionEfficiency))
                .collect(Collectors.toList());
    }

    public double getAbsorptionEfficiency() {
        return absorptionEfficiency;
    }

    public int getSpiralTurns() {
        return spiralTurns;
    }
}
