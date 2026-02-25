package com.sharkitecture.circulatory;

import com.sharkitecture.respiratory.GasExchange;

/**
 * The shark's blood carries oxygen from the gills to the muscles and organs.
 * Shark blood contains urea to maintain osmotic balance with seawater.
 */
public class BloodStream {

    private double oxygenLevel;
    private double ureaConcentration;

    public BloodStream() {
        this.oxygenLevel = 0.0;
        this.ureaConcentration = 350.0; // mmol/L, typical for sharks
    }

    public void oxygenate(GasExchange gasExchange) {
        this.oxygenLevel += gasExchange.getOxygenAbsorbed();
    }

    public double deliverOxygen(double demand) {
        double delivered = Math.min(oxygenLevel, demand);
        oxygenLevel -= delivered;
        return delivered;
    }

    public boolean isHypoxic() {
        return oxygenLevel < 0.2;
    }

    public double getOxygenLevel() {
        return oxygenLevel;
    }

    public double getUreaConcentration() {
        return ureaConcentration;
    }
}
