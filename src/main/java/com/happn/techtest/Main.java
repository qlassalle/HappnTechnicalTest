package com.happn.techtest;

import com.happn.techtest.controller.PointOfInterestController;
import com.happn.techtest.input.DensestZoneInput;
import com.happn.techtest.input.ZoneInput;
import com.happn.techtest.model.PointOfInterest;
import com.happn.techtest.util.JsonFormatter;
import com.happn.techtest.util.Parser;

import java.util.List;

public class Main {

    private static final PointOfInterestController controller = new PointOfInterestController();
    private static final String INPUT_FILE = "src/main/resources/input.tsv";

    public static void main(String[] args) {
        Parser parser = Parser.getInstance();
        List<PointOfInterest> pois = parser.getPointOfInterestsFromInputFile(INPUT_FILE);
        String arg = args[1].replace("'", "");
        if (args[0].equals("--nbpoi")) {
            ZoneInput input = JsonFormatter.fromJson(arg, ZoneInput.class);
            System.out.println(controller.getNumberOfPOIsInZone(pois, input.getMinLat(), input.getMinLon()));
        } else if (args[0].equals("--densest")) {
            DensestZoneInput input = JsonFormatter.fromJson(arg, DensestZoneInput.class);
            System.out.println(controller.getNDensestZone(pois, input.getN()));
        } else {
            throw new UnsupportedOperationException("Unknown flag cannot be handled");
        }
    }
}
