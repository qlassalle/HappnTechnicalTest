package com.happn.techtest.service;

import com.happn.techtest.model.GridZone;
import com.happn.techtest.model.PointOfInterest;
import com.happn.techtest.model.Zone;

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

        double lowerBoundary = posAsBigDecimal.intValue(); // rounded position: 6.2 -> 6, -7.8 -> -7
        // we have to think about negative values and handle them correctly
        if (decimal.compareTo(BigDecimal.ZERO) < 0) {
            // for decimals above -0.5, we have to add -1 to the rounded position
            if(decimal.compareTo(BigDecimal.valueOf(-0.5)) < 0) {
                lowerBoundary -= 1; // we subtract 1 to find the correct lowerBoundary: -7.8 -> -8
            } else {
                lowerBoundary -= 0.5; // we subtract -0.5 to find the correct lowerBoundary: -6.2 -> -6.5
            }
            return new Zone(lowerBoundary);
        }

        // just need to handle case with decimal > 0.5 that will result in a lowerBoundary equals to X.5
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
