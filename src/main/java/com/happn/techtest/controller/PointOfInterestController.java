package com.happn.techtest.controller;

import com.happn.techtest.model.PointOfInterest;
import com.happn.techtest.service.PointOfInterestService;
import com.happn.techtest.util.JsonFormatter;

import java.util.List;

public class PointOfInterestController {

    private final PointOfInterestService service = new PointOfInterestService();

    public String getNDensestZone(List<PointOfInterest> pois, int n) {
        return JsonFormatter.objectAsJson(service.getNDensestZone(pois, n));
    }

    public String getNumberOfPOIsInZone(List<PointOfInterest> pois, double minLat, double minLon) {
        return JsonFormatter.simpleKeyValueMap("value", service.getNumberOfPOIInZone(pois, minLat, minLon));
    }
}
