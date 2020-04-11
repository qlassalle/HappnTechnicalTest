package com.happn.techtest.utils;

import com.happn.techtest.models.PointOfInterest;
import com.sun.xml.internal.rngom.parse.Parseable;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private static final int DATA_STARTING_LINE = 1;
    private static Parser INSTANCE;
    private final TsvParser parser;

    private Parser() {
        this.parser = initializeParser();
    }

    public static Parser getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Parser();
        }
        return INSTANCE;
    }

    private TsvParser initializeParser() {
        TsvParserSettings settings = new TsvParserSettings();
        settings.getFormat().setLineSeparator("\n");

        return new TsvParser(settings);
    }

    public List<PointOfInterest> getPointOfInterestsFromInputFile(String filename) {
        List<String[]> allRows = parser.parseAll(new File(filename));
        List<PointOfInterest> parsedObjects = new ArrayList<>();
        for (int i = DATA_STARTING_LINE; i < allRows.size(); i++) {
            parsedObjects.add(new PointOfInterest(allRows.get(i)));
        }

        return parsedObjects;
    }
}
