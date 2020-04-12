package com.happn.techtest;

import com.happn.techtest.controller.PointOfInterestController;
import com.happn.techtest.model.PointOfInterest;
import com.happn.techtest.util.Parser;

import java.util.List;

public class Main {

    private static final PointOfInterestController controller = new PointOfInterestController();

    public static void main(String[] args) {
        Parser parser = Parser.getInstance();
        List<PointOfInterest> pois = parser.getPointOfInterestsFromInputFile("src/main/resources/input.tsv");
        System.out.println(controller.getNDensestZone(pois, 2));
        System.out.println(controller.getNumberOfPOIsInZone(pois, 6.5, -7));
    }
}
