package com.happn.techtest.service;

import com.happn.techtest.model.GridZone;
import com.happn.techtest.model.PointOfInterest;

import java.util.*;

public class PointOfInterestService {

    private final GridZoneService gridZoneService = new GridZoneService();

    public int getNumberOfPOIInZone (List<PointOfInterest> pois, double minLat, double minLon) {
        int numberOfPOIs = 0;
        for (PointOfInterest pointOfInterest : pois) {
            if (pointOfInterest.getLat() > minLat && pointOfInterest.getLon() > minLon) {
                ++numberOfPOIs;
            }
        }

        return numberOfPOIs;
    }

    /**
     * Retrieve the N densest zone from a list of {@link PointOfInterest}. Create first a Map containing for each
     * zone the number of {@link PointOfInterest} in it. We then store in a bucket (an array) the PointOfInterest
     * that appears at each frequency. This gives us the ordered list but in reverse order. That's why we iterate
     * from end to start in order to return the N densest zone. More explanations in the README file.
     * @param pois  The list of {@link PointOfInterest}
     * @param n     The number of densest zone wanted
     * @return      A list of {@link GridZone} representing the densest zone in ascending order
     */
    public List<GridZone> getNDensestZone(List<PointOfInterest> pois, int n) {
        Map<GridZone, Integer> zoneFrequency = getZoneFrequency(pois);
        // if points are very compact, we can have less zones than required so in order to avoid exceptions, we
        // simply return the list of zones we have
        if (n >= zoneFrequency.size()) {
            return new ArrayList<>(zoneFrequency.keySet());
        }
        // use a LinkedList to keep order insertion and provide ascending order
        List<GridZone> result = new LinkedList<>();
        List<GridZone>[] bucket = new List[zoneFrequency.size()];
        for(Map.Entry<GridZone, Integer> entry : zoneFrequency.entrySet()) {
            if (bucket[entry.getValue()] == null) {
                bucket[entry.getValue()] = new ArrayList<>();
            }
            bucket[entry.getValue()].add(entry.getKey());
        }

        for (int i = bucket.length - 1; i >= 0 && result.size() < n; i--) {
            if (bucket[i] == null) {
                continue;
            }
            // we don't simply dump the content of the bucket at this index because there may be too many entries and
            // we won't respect the condition result.size() == n
            for(int j = 0; j < bucket[i].size() && result.size() < n; j++) {
                result.add(bucket[i].get(j));
            }
        }

        return result;
    }

    private Map<GridZone, Integer> getZoneFrequency(List<PointOfInterest> pois) {
        Map<GridZone, Integer> grid = new HashMap<>();
        for (PointOfInterest pointOfInterest : pois) {
            GridZone gridZone = gridZoneService.createGridZoneFromPOI(pointOfInterest);
            grid.put(gridZone, grid.getOrDefault(gridZone, 0) + 1);
        }

        return grid;
    }
}
