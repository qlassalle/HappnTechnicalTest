package com.happn.techtest.util;

import com.happn.techtest.model.PointOfInterest;
import lombok.AllArgsConstructor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    @ParameterizedTest
    @MethodSource("defineTestCases")
    public void shouldCreateCorrectListOfPOI(POITestCase testCase) {
        // given is from MethodSource and our parameterized test
        // when
        Parser parser = Parser.getInstance();
        List<PointOfInterest> actualPOIs = parser.getPointOfInterestsFromInputFile(testCase.filename);
        // then
        assertEquals(testCase.pointOfInterests, actualPOIs);
    }

    private static Stream<Arguments> defineTestCases() {
        return Stream.of(
            Arguments.of(
                new POITestCase(
                    "src/test/resources/input.tsv",
                    Arrays.asList(
                        new PointOfInterest(new String[] {"1", "-48.6","-37.7"}),
                        new PointOfInterest(new String[] {"2", "-27.1","8.4"}),
                        new PointOfInterest(new String[] {"3", "6.6","-6.9"})
                    ),
                    "Input with positive and negative values")),
            Arguments.of(
                new POITestCase(
                    "src/test/resources/only_positive_input.tsv",
                    Arrays.asList(
                        new PointOfInterest(new String[] {"1", "22.2","31.4"}),
                        new PointOfInterest(new String[] {"2", "27.1","8.4"}),
                        new PointOfInterest(new String[] {"3", "6.6","6.9"})
                    ),
                    "Input with only positive values")
            ),
            Arguments.of(
                new POITestCase(
                    "src/test/resources/only_negative_input.tsv",
                    Arrays.asList(
                        new PointOfInterest(new String[] {"1", "-11.1","-109.4"}),
                        new PointOfInterest(new String[] {"2", "-0.1","-18.4"}),
                        new PointOfInterest(new String[] {"3", "-72.6","-96.9"}),
                        new PointOfInterest(new String[] {"4", "-178.3","-178.0"}),
                        new PointOfInterest(new String[] {"5", "-90.1","-8.7"})
                    ),
                    "Input with only negative values")
            )
        );
    }

    @AllArgsConstructor
    private static class POITestCase {
        private String filename;
        private List<PointOfInterest> pointOfInterests;
        private final String caseName;

        @Override
        public String toString() {
            return caseName;
        }
    }
}