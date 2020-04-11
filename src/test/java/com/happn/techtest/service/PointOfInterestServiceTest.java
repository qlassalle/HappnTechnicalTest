package com.happn.techtest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @ParameterizedTest
    @MethodSource("defineTestCases")
    public void shouldReturnCorrectNumberOfPOIInZone(PointOfInterestServiceTestCase testCase) throws JsonProcessingException {
        // given
        String expectedResult = mapper.writeValueAsString(Collections.singletonMap("value", testCase.expectedResult));
        // when
        String numberOfPOIInZone = service.getNumberOfPOIInZone(testCase.pois, testCase.minLat, testCase.minLon);
        // then
        assertEquals(expectedResult, numberOfPOIInZone);
    }

    private static Stream<Arguments> defineTestCases() {
        return Stream.of(
            Arguments.of(
                new PointOfInterestServiceTestCase(Arrays.asList(
                    new PointOfInterest(new String[] {"1", "-27.3","8.1"}),
                    new PointOfInterest(new String[] {"2", "-27.1","8.4"}),
                    new PointOfInterest(new String[] {"3", "6.6","-6.9"})
                ), -27.5, 8, 2)
            ),
            Arguments.of(
                new PointOfInterestServiceTestCase(Arrays.asList(
                    new PointOfInterest(new String[] {"1", "-27.3","8.1"}),
                    new PointOfInterest(new String[] {"2", "-27.1","8.4"}),
                    new PointOfInterest(new String[] {"3", "6.6","-6.9"})
                ), -27.5, 8.5, 0)
            ),
            Arguments.of(
                new PointOfInterestServiceTestCase(Arrays.asList(
                    new PointOfInterest(new String[] {"1", "-27.3","8.1"}),
                    new PointOfInterest(new String[] {"2", "29","-7.1"}),
                    new PointOfInterest(new String[] {"3", "6.6","-6.9"})
                ), 6.5, -7, 1)
            ),
            Arguments.of(
                new PointOfInterestServiceTestCase(Arrays.asList(
                    new PointOfInterest(new String[] {"1", "-27.3","8.1"}),
                    new PointOfInterest(new String[] {"2", "-27.1","8.4"}),
                    new PointOfInterest(new String[] {"3", "6.6","-6.9"})
                ), -27.5, -7, 3)
            )
        );
    }

    @AllArgsConstructor
    private static class PointOfInterestServiceTestCase {
        private List<PointOfInterest> pois;
        private double minLat;
        private double minLon;
        private int expectedResult;
    }
}