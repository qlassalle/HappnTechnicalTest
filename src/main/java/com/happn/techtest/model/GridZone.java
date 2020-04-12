package com.happn.techtest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor
@Getter
public class GridZone {

    @JsonProperty("min_lat")
    private double minLat;
    @JsonProperty("max_lat")
    private double maxLat;
    @JsonProperty("min_lon")
    private double minLon;
    @JsonProperty("max_lon")
    private double maxLon;

    public GridZone(Zone latZone, Zone lonZone) {
        this.minLat = latZone.getLowerBoundary();
        this.maxLat = latZone.getUpperBoundary();
        this.minLon = lonZone.getLowerBoundary();
        this.maxLon = lonZone.getUpperBoundary();
    }
}
