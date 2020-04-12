package com.happn.techtest.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class Zone {
    private  static final double ZONE_AREA = 0.5;

    private double lowerBoundary;
    private double upperBoundary;

    /**
     * Default constructor for a zone. We do not need to pass the upper boundary as a parameter because it will
     * always be the lower boundary + 0.5
     * @param lowerBoundary The lower boundary
     */
    public Zone(double lowerBoundary) {
        this.lowerBoundary = lowerBoundary;
        this.upperBoundary = this.lowerBoundary + ZONE_AREA;
    }
}
