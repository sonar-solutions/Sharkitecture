package com.sharkitecture.circulatory;

import com.sharkitecture.respiratory.GasExchange;
import com.sharkitecture.respiratory.Gill;

/**
 * The shark heart has only 2 chambers (atrium and ventricle),
 * making it one of the simplest vertebrate hearts. It pumps
 * deoxygenated blood to the gills, where it gets oxygenated
 * before flowing to the rest of the body.
 */
public class Heart {

    private final int beatsPerMinute;
    private final BloodStream bloodStream;

    public Heart(int beatsPerMinute, BloodStream bloodStream) {
        this.beatsPerMinute = beatsPerMinute;
        this.bloodStream = bloodStream;
    }

    public void pump(Gill gill, double waterFlowRate, double waterOxygenContent) {
        GasExchange exchange = gill.extractOxygen(waterFlowRate, waterOxygenContent);
        bloodStream.oxygenate(exchange);
    }

    public double getCardiacOutput() {
        return beatsPerMinute * 0.05;
    }

    public int getBeatsPerMinute() {
        return beatsPerMinute;
    }

    public BloodStream getBloodStream() {
        return bloodStream;
    }
}
