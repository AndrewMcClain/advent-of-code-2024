package com.amcclain.advent2024;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class SafeReportsTest {

    @ParameterizedTest
    @MethodSource("expectedResults")
    void testReportSafety(List<Integer> report, boolean expected) {
        assert(SafeReports.isSafeReport(report) == expected);
    }

    @ParameterizedTest
    @MethodSource("expectedDampnerResults")
    void testDampenerReportSafety(List<Integer> report, boolean expected) {
        assert(SafeReports.isSafeWithDampener(report) == expected);
    }

    static Stream<Arguments> expectedResults() {
        return Stream.of(
                Arguments.of(List.of(7, 6, 4, 2, 1), true),
                Arguments.of(List.of(1, 2, 7, 8, 9), false),
                Arguments.of(List.of(9, 7, 6, 2, 1), false),
                Arguments.of(List.of(1, 3, 2, 4, 5), false),
                Arguments.of(List.of(8, 6, 4, 4, 1), false),
                Arguments.of(List.of(1, 3, 6, 7, 9), true)
        );
    }

    static Stream<Arguments> expectedDampnerResults() {
        return Stream.of(
                Arguments.of(List.of(7, 6, 4, 2, 1), true),
                Arguments.of(List.of(1, 2, 7, 8, 9), false),
                Arguments.of(List.of(9, 7, 6, 2, 1), false),
                Arguments.of(List.of(1, 3, 2, 4, 5), true),
                Arguments.of(List.of(8, 6, 4, 4, 1), true),
                Arguments.of(List.of(1, 3, 6, 7, 9), true)
        );
    }

}
