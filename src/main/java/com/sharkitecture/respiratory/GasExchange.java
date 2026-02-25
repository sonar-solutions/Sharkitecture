package com.sharkitecture.respiratory;

/**
 * Represents the result of gas exchange at the gill surface.
 * Oxygen is absorbed from the water while carbon dioxide is expelled.
 */
public class GasExchange {

    private final double oxygenAbsorbed;
    private final double carbonDioxideExpelled;

    public GasExchange(double oxygenAbsorbed, double carbonDioxideExpelled) {
        this.oxygenAbsorbed = oxygenAbsorbed;
        this.carbonDioxideExpelled = carbonDioxideExpelled;
    }

    public double getOxygenAbsorbed() {
        return oxygenAbsorbed;
    }

    public double getCarbonDioxideExpelled() {
        return carbonDioxideExpelled;
    }

    public double getEfficiency() {
        if (carbonDioxideExpelled == 0) {
            return 0;
        }
        return oxygenAbsorbed / (oxygenAbsorbed + carbonDioxideExpelled);
    }

    public boolean isSufficient(double minimumOxygen) {
        return oxygenAbsorbed >= minimumOxygen;
    }
}
