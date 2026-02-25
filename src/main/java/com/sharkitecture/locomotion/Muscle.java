package com.sharkitecture.locomotion;

import com.sharkitecture.skeleton.Cartilage;

/**
 * Shark muscles are arranged in W-shaped blocks called myomeres.
 * Red muscle fibers (slow-twitch) run along the body for sustained cruising,
 * while white muscle fibers (fast-twitch) provide burst speed for attacks.
 */
public class Muscle {

    public enum FiberType {
        RED_SLOW_TWITCH, WHITE_FAST_TWITCH
    }

    private final FiberType fiberType;
    private final double massKg;
    private final Cartilage attachment;
    private double fatigue;

    public Muscle(FiberType fiberType, double massKg, Cartilage attachment) {
        this.fiberType = fiberType;
        this.massKg = massKg;
        this.attachment = attachment;
        this.fatigue = 0.0;
    }

    public double contract(double intensity) {
        if (!attachment.canSupportLoad(intensity * massKg * 9.8)) {
            return 0.0;
        }

        double force = massKg * intensity * (1.0 - fatigue);

        fatigue += switch (fiberType) {
            case RED_SLOW_TWITCH -> intensity * 0.01;
            case WHITE_FAST_TWITCH -> intensity * 0.10;
        };
        fatigue = Math.min(fatigue, 1.0);

        return force;
    }

    public void rest(double duration) {
        double recoveryRate = fiberType == FiberType.RED_SLOW_TWITCH ? 0.05 : 0.02;
        fatigue = Math.max(0.0, fatigue - (recoveryRate * duration));
    }

    public double getFatigue() {
        return fatigue;
    }

    public FiberType getFiberType() {
        return fiberType;
    }
}
