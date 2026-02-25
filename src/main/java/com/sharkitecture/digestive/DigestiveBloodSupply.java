package com.sharkitecture.digestive;

import com.sharkitecture.circulatory.BloodStream;
import com.sharkitecture.circulatory.Heart;

/**
 * Dr. Franken-Shark mutation: the digestive system now directly
 * taps into the circulatory system to power acid production.
 *
 * This creates a dependency from digestive → circulatory.
 * Combined with NutrientTransport (circulatory → digestive),
 * this forms a TANGLE.
 */
public class DigestiveBloodSupply {

    private final BloodStream bloodStream;
    private final Heart heart;

    public DigestiveBloodSupply(BloodStream bloodStream, Heart heart) {
        this.bloodStream = bloodStream;
        this.heart = heart;
    }

    public boolean hasAdequateBloodFlow() {
        return !bloodStream.isHypoxic() && heart.getCardiacOutput() > 0.5;
    }

    public double requestOxygenForDigestion(double foodMassKg) {
        double oxygenNeeded = foodMassKg * 0.1;
        return bloodStream.deliverOxygen(oxygenNeeded);
    }

    public double getDigestiveEfficiency() {
        double oxygenAvailable = bloodStream.getOxygenLevel();
        return Math.min(1.0, oxygenAvailable / 0.5);
    }
}
