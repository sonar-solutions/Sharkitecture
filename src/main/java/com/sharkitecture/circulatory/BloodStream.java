package com.sharkitecture.circulatory;

import com.sharkitecture.digestive.Nutrient;
import com.sharkitecture.respiratory.GasExchange;

import java.util.List;

/**
 * The shark's blood carries oxygen from the gills to the muscles and organs.
 * Shark blood contains urea to maintain osmotic balance with seawater.
 *
 * Dr. Franken-Shark modification: blood now absorbs nutrients directly
 * from the digestive system, creating a dependency on the digestive package.
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

    /**
     * Absorb nutrients directly into the bloodstream.
     * This creates a dependency from circulatory → digestive.
     * Combined with Stomach's dependency on BloodStream, this forms a TANGLE.
     */
    public void absorbNutrients(List<Nutrient> nutrients) {
        for (Nutrient n : nutrients) {
            this.oxygenLevel += n.getEnergyKcal() * 0.001;
        }
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
