package com.happn.techtest.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@AllArgsConstructor
public class GridZone {

    private double minLat;
    private double maxLat;
    private double minLon;
    private double maxLon;

    public GridZone(Zone latZone, Zone lonZone) {
        this.minLat = latZone.getLowerBoundary();
        this.maxLat = latZone.getUpperBoundary();
        this.minLon = lonZone.getLowerBoundary();
        this.maxLon = lonZone.getUpperBoundary();
    }
}
