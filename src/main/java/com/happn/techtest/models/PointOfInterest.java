package com.happn.techtest.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class PointOfInterest {

    private final int id;
    private final double lat;
    private final double lon;

    public PointOfInterest(String[] poiAsTsvRow) {
        this.id = Integer.parseInt(poiAsTsvRow[0]);
        this.lat = Double.parseDouble(poiAsTsvRow[1]);
        this.lon = Double.parseDouble(poiAsTsvRow[2]);
    }
}
