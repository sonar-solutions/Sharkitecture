package com.sharkitecture;

import com.sharkitecture.brain.Brain;
import com.sharkitecture.brain.NeuralSignal;
import com.sharkitecture.circulatory.BloodStream;
import com.sharkitecture.circulatory.Heart;
import com.sharkitecture.digestive.Nutrient;
import com.sharkitecture.digestive.SpiralValveIntestine;
import com.sharkitecture.digestive.Stomach;
import com.sharkitecture.locomotion.CaudalFin;
import com.sharkitecture.locomotion.DorsalFin;
import com.sharkitecture.locomotion.PectoralFin;
import com.sharkitecture.respiratory.Gill;
import com.sharkitecture.sensory.Electroreception;
import com.sharkitecture.sensory.LateralLine;
import com.sharkitecture.sensory.SensoryInput;
import com.sharkitecture.skeleton.Jaw;

import java.util.ArrayList;
import java.util.List;

/**
 * A Great White Shark (Carcharodon carcharias).
 * This class composes all body systems into a functioning apex predator.
 *
 * Clean dependency flow:
 *   brain → sensory      (brain reads sensory input)
 *   brain → locomotion   (brain controls movement)
 *   circulatory → respiratory  (heart pumps blood through gills)
 *   locomotion → skeleton      (muscles attach to cartilage)
 */
public class Shark {

    private final String name;

    // Sensory system
    private final Electroreception electroreception;
    private final LateralLine lateralLine;

    // Skeleton
    private final Jaw jaw;

    // Respiratory system
    private final Gill leftGills;
    private final Gill rightGills;

    // Circulatory system
    private final Heart heart;
    private final BloodStream bloodStream;

    // Digestive system
    private final Stomach stomach;
    private final SpiralValveIntestine intestine;

    // Locomotion
    private final CaudalFin caudalFin;
    private final DorsalFin dorsalFin;
    private final PectoralFin leftPectoralFin;
    private final PectoralFin rightPectoralFin;

    // Brain
    private final Brain brain;

    public Shark(String name) {
        this.name = name;

        this.electroreception = new Electroreception(1500);
        this.lateralLine = new LateralLine(1.2);

        this.jaw = new Jaw(5, 18000.0);

        this.leftGills = new Gill(5, 2000.0);
        this.rightGills = new Gill(5, 2000.0);

        this.bloodStream = new BloodStream();
        this.heart = new Heart(25, bloodStream);

        this.stomach = new Stomach(8.0);
        this.intestine = new SpiralValveIntestine(7);

        this.caudalFin = new CaudalFin(60.0);
        this.dorsalFin = new DorsalFin(30.0);
        this.leftPectoralFin = new PectoralFin(PectoralFin.Side.LEFT, 45.0);
        this.rightPectoralFin = new PectoralFin(PectoralFin.Side.RIGHT, 45.0);

        this.brain = new Brain(0.8);
    }

    /**
     * The core shark lifecycle loop: breathe → sense → think → act.
     */
    public void swim(double waterFlowRate, double waterOxygenContent,
                     double[] electricalReadings, double[] pressureReadings) {
        // 1. Breathe: Heart pumps blood through gills
        heart.pump(leftGills, waterFlowRate, waterOxygenContent);
        heart.pump(rightGills, waterFlowRate, waterOxygenContent);

        // 2. Sense: Gather environmental data
        List<SensoryInput> inputs = new ArrayList<>();
        inputs.addAll(electroreception.scan(electricalReadings));
        inputs.addAll(lateralLine.detectVibrations(pressureReadings));

        // 3. Think: Brain processes input and decides action
        NeuralSignal signal = brain.processSensoryInput(inputs);

        // 4. Act: Execute the brain's command
        brain.executeSignal(signal, caudalFin, leftPectoralFin, rightPectoralFin);
    }

    public List<Nutrient> eat(String prey) {
        jaw.bite();
        List<Nutrient> rawNutrients = stomach.digest(prey);
        return intestine.absorb(rawNutrients);
    }

    public String getName() {
        return name;
    }

    public Brain getBrain() {
        return brain;
    }

    public Heart getHeart() {
        return heart;
    }

    public Jaw getJaw() {
        return jaw;
    }

    @Override
    public String toString() {
        return String.format("Shark[%s] - %d teeth, %.0f N bite force, %.1f km/h max speed",
                name, jaw.getActiveTeeth(), jaw.bite(), caudalFin.getMaxSpeed());
    }

    public static void main(String[] args) {
        Shark bruce = new Shark("Bruce");
        System.out.println(bruce);

        double[] electricalField = {0.0, 0.1, 0.5, 0.0, 0.0, 0.0, 0.0, 0.0};
        double[] pressureField = {0.0, 0.0, 0.0, 0.2, 0.0, 0.0, 0.0, 0.0};

        bruce.swim(2.0, 0.8, electricalField, pressureField);
        System.out.println("Signal: " + bruce.getBrain().getLastSignal().getCommand());

        List<Nutrient> nutrients = bruce.eat("seal");
        double totalEnergy = nutrients.stream().mapToDouble(Nutrient::getEnergyKcal).sum();
        System.out.printf("Consumed seal: %.1f kcal absorbed%n", totalEnergy);
    }
}
