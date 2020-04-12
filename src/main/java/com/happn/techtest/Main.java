package com.happn.techtest;

import com.happn.techtest.controller.PointOfInterestController;
import com.happn.techtest.input.DensestZoneInput;
import com.happn.techtest.input.ZoneInput;
import com.happn.techtest.model.PointOfInterest;
import com.happn.techtest.util.JsonFormatter;
import com.happn.techtest.util.Parser;

import java.util.List;

import static spark.Spark.post;

public class Main {

    private static final PointOfInterestController controller = new PointOfInterestController();
    private static final String INPUT_FILE = "src/main/resources/input.tsv";

    public static void main(String[] args) {
        Parser parser = Parser.getInstance();
        List<PointOfInterest> pois = parser.getPointOfInterestsFromInputFile(INPUT_FILE);
        if (args.length > 0) {
            handleCLIArguments(pois, args);
            return;
        }
        setupSparkServer(pois);
    }

    private static void setupSparkServer(List<PointOfInterest> pois) {
        post("/nbpoi", (req, res) -> {
            ZoneInput input = JsonFormatter.fromJson(req.body(), ZoneInput.class);
            return controller.getNumberOfPOIsInZone(pois, input.getMinLat(), input.getMinLon());
        });
        post("/densest", (req, res) -> {
            DensestZoneInput input = JsonFormatter.fromJson(req.body(), DensestZoneInput.class);
            return controller.getNDensestZone(pois, input.getN());
        });
    }

    private static void handleCLIArguments(List<PointOfInterest> pois, String[] args) {
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
