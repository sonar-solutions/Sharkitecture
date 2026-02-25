package com.sharkitecture.brain;

/**
 * Represents a command signal sent from the brain to body systems.
 */
public class NeuralSignal {

    public enum Command {
        CRUISE, ACCELERATE, TURN_LEFT, TURN_RIGHT,
        ATTACK, DIVE, SURFACE, BRAKE, IDLE
    }

    private final Command command;
    private final double intensity;

    public NeuralSignal(Command command, double intensity) {
        this.command = command;
        this.intensity = Math.max(0.0, Math.min(1.0, intensity));
    }

    public Command getCommand() {
        return command;
    }

    public double getIntensity() {
        return intensity;
    }

    public boolean isUrgent() {
        return intensity > 0.7;
    }
}
