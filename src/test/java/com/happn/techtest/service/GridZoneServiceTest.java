package com.happn.techtest.service;

import com.happn.techtest.models.Zone;
import lombok.AllArgsConstructor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GridZoneServiceTest {

    private final GridZoneService gridZoneService = new GridZoneService();

    @ParameterizedTest
    @MethodSource("defineTestCases")
    public void shouldDefineCorrectZoneForPosition(GridZoneTestCase testCase) {
        // when
        Zone actualZone = gridZoneService.definePointInGrid(testCase.position);
        // then
        assertEquals(testCase.expectedZone, actualZone);
    }

    private static Stream<Arguments> defineTestCases() {
        return Stream.of(
            Arguments.of(
                new GridZoneTestCase(6.3, new Zone(6.0), "Positive position with low decimal")
            ),
            Arguments.of(
                new GridZoneTestCase(0.7, new Zone(0.5), "Positive position with high decimal")
            ),
            Arguments.of(
                new GridZoneTestCase(-12.3, new Zone(-12.5), "Negative position with low decimal")
            ),
            Arguments.of(
                new GridZoneTestCase(-39.9, new Zone(-40.0), "Negative position with high decimal")
            ),
            Arguments.of(
                new GridZoneTestCase(50.5, new Zone(50), "Positive position with half decimal")
            ),
            Arguments.of(
                new GridZoneTestCase(-2.5, new Zone(-2.5), "Negative position with half decimal")
            )
        );
    }

    @AllArgsConstructor
    private static class GridZoneTestCase {
        private final double position;
        private final Zone expectedZone;
        private final String caseName;

        @Override
        public String toString() {
            return caseName;
        }
    }
}