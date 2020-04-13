package com.happn.techtest.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor
@Getter
public class GridZone {

    private final double minLat;
    private final double maxLat;
    private final double minLon;
    private final double maxLon;

    public GridZone(Zone latZone, Zone lonZone) {
        this.minLat = latZone.getLowerBoundary();
        this.maxLat = latZone.getUpperBoundary();
        this.minLon = lonZone.getLowerBoundary();
        this.maxLon = lonZone.getUpperBoundary();
    }
}
