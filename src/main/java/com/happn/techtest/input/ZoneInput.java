package com.happn.techtest.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ZoneInput {

    @JsonProperty("min_lat")
    private double minLat;
    @JsonProperty("min_lon")
    private double minLon;
}
