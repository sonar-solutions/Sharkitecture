package com.sharkitecture.digestive;

import java.util.ArrayList;
import java.util.List;

/**
 * Sharks can evert (turn inside out) their stomachs to expel
 * indigestible items. Their stomach acid is strong enough to
 * dissolve metal — a pH as low as 1.0.
 */
public class Stomach {

    private static final double ACID_PH = 1.5;
    private final double volumeLiters;
    private final List<String> contents;

    public Stomach(double volumeLiters) {
        this.volumeLiters = volumeLiters;
        this.contents = new ArrayList<>();
    }

    public List<Nutrient> digest(String prey) {
        contents.add(prey);

        List<Nutrient> nutrients = new ArrayList<>();
        double preyMassKg = estimatePreyMass(prey);

        nutrients.add(new Nutrient(Nutrient.NutrientType.PROTEIN, preyMassKg * 0.20));
        nutrients.add(new Nutrient(Nutrient.NutrientType.FAT, preyMassKg * 0.15));
        nutrients.add(new Nutrient(Nutrient.NutrientType.MINERAL, preyMassKg * 0.03));

        return nutrients;
    }

    public void evertStomach() {
        contents.clear();
    }

    public boolean isFull() {
        return contents.size() >= 3;
    }

    private double estimatePreyMass(String prey) {
        return switch (prey.toLowerCase()) {
            case "seal" -> 80.0;
            case "fish" -> 2.0;
            case "squid" -> 5.0;
            case "turtle" -> 40.0;
            default -> 10.0;
        };
    }

    public double getVolumeLiters() {
        return volumeLiters;
    }
}
