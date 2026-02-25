package com.sharkitecture.circulatory;

import com.sharkitecture.digestive.Nutrient;
import com.sharkitecture.digestive.Stomach;
import com.sharkitecture.digestive.SpiralValveIntestine;

import java.util.List;

/**
 * Dr. Franken-Shark mutation: the circulatory system now directly
 * pulls nutrients from the digestive system and distributes them.
 *
 * This creates a dependency from circulatory → digestive.
 * Combined with DigestiveBloodSupply (digestive → circulatory),
 * this forms a TANGLE.
 */
public class NutrientTransport {

    private final Stomach stomach;
    private final SpiralValveIntestine intestine;
    private final BloodStream bloodStream;

    public NutrientTransport(Stomach stomach, SpiralValveIntestine intestine, BloodStream bloodStream) {
        this.stomach = stomach;
        this.intestine = intestine;
        this.bloodStream = bloodStream;
    }

    public double transportNutrients(String prey) {
        List<Nutrient> rawNutrients = stomach.digest(prey);
        List<Nutrient> absorbed = intestine.absorb(rawNutrients);

        double totalEnergy = 0;
        for (Nutrient nutrient : absorbed) {
            totalEnergy += nutrient.getEnergyKcal();
        }

        return totalEnergy;
    }

    public boolean isDigestionActive() {
        return !stomach.isFull() && !bloodStream.isHypoxic();
    }
}
