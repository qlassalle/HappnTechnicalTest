package com.happn.techtest;

import com.happn.techtest.models.PointOfInterest;
import com.happn.techtest.service.PointOfInterestService;
import com.happn.techtest.utils.Parser;

import java.util.List;

public class Main {

    private static final PointOfInterestService service = new PointOfInterestService();

    public static void main(String[] args) {
        Parser parser = Parser.getInstance();
        List<PointOfInterest> pois = parser.getPointOfInterestsFromInputFile("src/main/resources/input.tsv");
        service.getNDensestZone(pois, 2);
    }

}
