package com.happn.techtest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.happn.techtest.models.GridZone;
import com.happn.techtest.models.PointOfInterest;
import lombok.AllArgsConstructor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointOfInterestServiceTest {

    private final PointOfInterestService service = new PointOfInterestService();
    private final ObjectMapper mapper = new ObjectMapper();

    @ParameterizedTest(name = "{0}")
    @MethodSource("numberOfPOIsInZoneTestCase")
    public void shouldReturnCorrectNumberOfPOIInZone(PointOfInterestServiceTestCase testCase) throws JsonProcessingException {
        // given
        String expectedResult = mapper.writeValueAsString(Collections.singletonMap("value", testCase.expectedResult));
        // when
        String numberOfPOIInZone = service.getNumberOfPOIInZone(testCase.pois, testCase.minLat, testCase.minLon);
        // then
        assertEquals(expectedResult, numberOfPOIInZone);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("densestZoneTestCase")
    public void shouldReturnCorrectNDensestZone(PointOfInterestServiceDensestZoneTestCase testCase) {
        // when
        List<GridZone> actualGridZone = service.getNDensestZone(testCase.pois, testCase.n);
        // then
        assertEquals(testCase.expectedGridZoneList, actualGridZone);
    }

    private static Stream<Arguments> densestZoneTestCase() {
        return Stream.of(
            Arguments.of(
                new PointOfInterestServiceDensestZoneTestCase(
                    Arrays.asList(
                        generatePointOfInterest("1", "-27.3", "8.1"),
                        generatePointOfInterest("2", "-27.1", "8.4"),
                        generatePointOfInterest("3", "-27.2", "8.2")
                    ),
                    2,
                    Collections.singletonList(generateGridZone(-27.5, -27.0, 8.0, 8.5)),
                    "All points in same region but n greater than 1")
            ),
            Arguments.of(
                new PointOfInterestServiceDensestZoneTestCase(
                    Arrays.asList(
                        generatePointOfInterest("1", "-27.3", "8.1"),
                        generatePointOfInterest("2", "-40.1", "18.4"),
                        generatePointOfInterest("3", "-40.2", "18.2")
                    ),
                    2,
                    Arrays.asList(generateGridZone(-40.5, -40.0, 18.0, 18.5),
                                    generateGridZone(-27.5, -27.0, 8.0, 8.5)),
                    "Should return two zones"
                    )
            ),
            Arguments.of(
                new PointOfInterestServiceDensestZoneTestCase(
                    Arrays.asList(
                        generatePointOfInterest("1", "-48.6", "-37.7"),
                        generatePointOfInterest("2", "-27.1", "8.4"),
                        generatePointOfInterest("3", "6.6", "-6.9"),
                        generatePointOfInterest("4", "-2.3", "38.3"),
                        generatePointOfInterest("5", "6.8", "-6.9"),
                        generatePointOfInterest("6", "-2.5", "38.3"),
                        generatePointOfInterest("7", "0.1", "-0.1"),
                        generatePointOfInterest("8", "-2.1", "38.1")
                    ),
                    2,
                    Arrays.asList(generateGridZone(-2.5, -2.0, 38, 38.5),
                                    generateGridZone(6.5, 7.0, -7.0, -6.5)),
                    "Should behave as specifications provided in the handout"
                )
            )
        );
    }

    private static Stream<Arguments> numberOfPOIsInZoneTestCase() {
        return Stream.of(
            Arguments.of(
                new PointOfInterestServiceTestCase(
                    Arrays.asList(
                        generatePointOfInterest("1", "-27.3", "8.1"),
                        generatePointOfInterest("2", "-27.1", "8.4"),
                        generatePointOfInterest("3", "6.6", "-6.9")
                    ),
                    -27.5, 8,2,
                    "Should return correct number for expected position")
            ),
            Arguments.of(
                new PointOfInterestServiceTestCase(
                    Arrays.asList(
                        generatePointOfInterest("1", "-27.3", "8.1"),
                        generatePointOfInterest("2", "-27.1", "8.4"),
                        generatePointOfInterest("3", "6.6", "-6.9")
                    ),
                    -27.5, 8.5, 0,
                    "Should return 0 when no pointOfInterest in zone")
            ),
            Arguments.of(
                new PointOfInterestServiceTestCase(
                    Arrays.asList(
                        generatePointOfInterest("1", "-27.3", "8.1"),
                        generatePointOfInterest("2", "29", "-7.1"),
                        generatePointOfInterest("3", "6.6", "-6.9")
                    ),
                    6.5,-7, 1,
                    "Should return correct number for other expected position"
                    )
            ),
            Arguments.of(
                new PointOfInterestServiceTestCase(
                    Arrays.asList(
                        generatePointOfInterest("1", "-27.3", "8.1"),
                        generatePointOfInterest("2", "-27.1", "8.4"),
                        generatePointOfInterest("3", "6.6", "-6.9")
                    ),
                    -27.5,-7, 3,
                    "Should return correct number for large position"
                )
            )
        );
    }

    private static PointOfInterest generatePointOfInterest(String id, String lat, String lon) {
        return new PointOfInterest(new String[]{id, lat, lon});
    }

    private static GridZone generateGridZone(double minLat, double maxLat, double minLon, double maxLon) {
        return new GridZone(minLat, maxLat, minLon, maxLon);
    }

    @AllArgsConstructor
    private static class PointOfInterestServiceTestCase {
        private List<PointOfInterest> pois;
        private double minLat;
        private double minLon;
        private int expectedResult;
        private final String caseName;

        @Override
        public String toString() {
            return caseName;
        }
    }

    @AllArgsConstructor
    private static class PointOfInterestServiceDensestZoneTestCase {
        private final List<PointOfInterest> pois;
        private final int n;
        private final List<GridZone> expectedGridZoneList;
        private final String caseName;

        @Override
        public String toString() {
            return caseName;
        }
    }
}