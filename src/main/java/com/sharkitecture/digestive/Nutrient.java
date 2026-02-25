package com.sharkitecture.digestive;

public class Nutrient {

    public enum NutrientType {
        PROTEIN, FAT, MINERAL, VITAMIN
    }

    private final NutrientType type;
    private final double amount;

    public Nutrient(NutrientType type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public NutrientType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getEnergyKcal() {
        return switch (type) {
            case PROTEIN -> amount * 4.0;
            case FAT -> amount * 9.0;
            case MINERAL, VITAMIN -> 0.0;
        };
    }
}
