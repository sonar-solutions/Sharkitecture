package com.sharkitecture.brain;

import com.sharkitecture.sensory.SensoryInput;
import com.sharkitecture.locomotion.CaudalFin;
import com.sharkitecture.locomotion.PectoralFin;

import java.util.List;

/**
 * The shark brain processes sensory input and generates motor commands.
 * Approximately 2/3 of the shark brain is dedicated to smell processing.
 * The brain coordinates the "detect → decide → act" loop.
 */
public class Brain {

    private final double processingSpeed;
    private NeuralSignal lastSignal;

    public Brain(double processingSpeed) {
        this.processingSpeed = processingSpeed;
        this.lastSignal = new NeuralSignal(NeuralSignal.Command.IDLE, 0.0);
    }

    public NeuralSignal processSensoryInput(List<SensoryInput> inputs) {
        if (inputs.isEmpty()) {
            lastSignal = new NeuralSignal(NeuralSignal.Command.CRUISE, 0.3);
            return lastSignal;
        }

        SensoryInput strongest = inputs.stream()
                .reduce((a, b) -> a.getIntensity() > b.getIntensity() ? a : b)
                .orElse(inputs.get(0));

        NeuralSignal signal = decideAction(strongest);
        lastSignal = signal;
        return signal;
    }

    public void executeSignal(NeuralSignal signal, CaudalFin tail, PectoralFin leftFin, PectoralFin rightFin) {
        switch (signal.getCommand()) {
            case ACCELERATE, ATTACK -> tail.generateThrust(signal.getIntensity());
            case TURN_LEFT -> {
                tail.generateThrust(signal.getIntensity() * 0.5);
                leftFin.steer(-15.0 * signal.getIntensity());
            }
            case TURN_RIGHT -> {
                tail.generateThrust(signal.getIntensity() * 0.5);
                rightFin.steer(15.0 * signal.getIntensity());
            }
            case CRUISE -> tail.generateThrust(signal.getIntensity() * 0.3);
            case BRAKE -> { /* reduce speed via pectoral angling */ }
            default -> { /* idle */ }
        }
    }

    private NeuralSignal decideAction(SensoryInput input) {
        if (input.isThreat()) {
            return new NeuralSignal(NeuralSignal.Command.ACCELERATE, 1.0);
        }

        if (input.isPrey()) {
            double bearing = input.getBearing();
            if (bearing < 170) {
                return new NeuralSignal(NeuralSignal.Command.TURN_LEFT, input.getIntensity());
            } else if (bearing > 190) {
                return new NeuralSignal(NeuralSignal.Command.TURN_RIGHT, input.getIntensity());
            } else {
                return new NeuralSignal(NeuralSignal.Command.ATTACK, input.getIntensity());
            }
        }

        return new NeuralSignal(NeuralSignal.Command.CRUISE, 0.3);
    }

    public NeuralSignal getLastSignal() {
        return lastSignal;
    }

    public double getProcessingSpeed() {
        return processingSpeed;
    }
}
