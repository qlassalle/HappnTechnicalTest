package com.happn.techtest.service;

import com.happn.techtest.models.GridZone;
import com.happn.techtest.models.PointOfInterest;
import com.happn.techtest.models.Zone;

import java.math.BigDecimal;

public class GridZoneService {


    /**
     * Define a Zone for a position. A Zone is an area that spans in a 0.5 distance. Keep this method package
     * protected to ease unit testing.
     * @param pos   The position as a double
     * @return      A {@link Zone} object
     */
    Zone definePointInGrid(double pos) {
        BigDecimal posAsBigDecimal = BigDecimal.valueOf(pos);
        BigDecimal decimal = posAsBigDecimal.remainder(BigDecimal.ONE);

        double lowerBoundary = posAsBigDecimal.intValue();
        // we have to think about negative values and handle them correctly
        if (decimal.compareTo(BigDecimal.ZERO) < 0) {
            if(decimal.compareTo(BigDecimal.valueOf(-0.5)) < 0) {
                lowerBoundary -= 1;
            } else {
                lowerBoundary -= 0.5;
            }
            return new Zone(lowerBoundary);
        }

        if (decimal.compareTo(BigDecimal.valueOf(0.5)) > 0) {
            lowerBoundary = posAsBigDecimal.intValue() + 0.5;
        }

        return new Zone(lowerBoundary);
    }

    public GridZone createGridZoneFromPOI(PointOfInterest pointOfInterest) {
        Zone latZone = definePointInGrid(pointOfInterest.getLat());
        Zone lonZone = definePointInGrid(pointOfInterest.getLon());
        return new GridZone(latZone, lonZone);
    }
}
