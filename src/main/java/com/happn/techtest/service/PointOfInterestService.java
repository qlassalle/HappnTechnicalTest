package com.happn.techtest.service;

import com.happn.techtest.models.PointOfInterest;
import com.happn.techtest.utils.JsonFormatter;

import java.util.List;

public class PointOfInterestService {

    public String getNumberOfPOIInZone (List<PointOfInterest> pois, double minLat, double minLon) {
        int numberOfPOIs = 0;
        for (PointOfInterest pointOfInterest : pois) {
            if (pointOfInterest.getLat() > minLat && pointOfInterest.getLon() > minLon) {
                ++numberOfPOIs;
            }
        }
        return JsonFormatter.simpleKeyValueMap("value", numberOfPOIs);
    }
}
