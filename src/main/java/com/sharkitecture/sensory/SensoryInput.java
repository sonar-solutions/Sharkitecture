package com.sharkitecture.sensory;

public class SensoryInput {

    public enum InputType {
        ELECTRICAL, VIBRATION, CHEMICAL, VISUAL, THERMAL
    }

    private final InputType type;
    private final double intensity;
    private final double bearing;

    public SensoryInput(InputType type, double intensity, double bearing) {
        this.type = type;
        this.intensity = intensity;
        this.bearing = bearing;
    }

    public InputType getType() {
        return type;
    }

    public double getIntensity() {
        return intensity;
    }

    public double getBearing() {
        return bearing;
    }

    public boolean isThreat() {
        return intensity > 0.8;
    }

    public boolean isPrey() {
        return type == InputType.ELECTRICAL && intensity > 0.3 && intensity <= 0.8;
    }
}
