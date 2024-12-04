package com.amcclain.advent2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SafeReports {

    public static void main(String[] args) throws IOException {
        List<List<Integer>> reports = readInput();
        System.out.println("The number of safe reports is : " + filterSafeReports(reports).size());
    }

    public static List<List<Integer>> readInput() throws IOException {
        List<List<Integer>> reports = new ArrayList<>();
        Files.readAllLines(Paths.get("day-2/java/src/main/resources/input.txt")).forEach((line) -> {
            List<Integer> report = new ArrayList<>();
            Arrays.stream(line.splitWithDelimiters("\\s+", 0))
                    .filter((s) -> !s.matches("^\\s+$"))
                    .forEach((s) -> {
                report.add(Integer.parseInt(s));
            });
            reports.add(report);
        });
        return reports;
    }

    public static List<List<Integer>> filterSafeReports(List<List<Integer>> reports) {
        return reports.stream().filter(SafeReports::isSafeReport).collect(Collectors.toList());
    }

    public static boolean isSafeReport(List<Integer> report) {
        ReportTrend type = ReportTrend.INITIAL;
        int previous = report.get(0);
        for( int i = 1; i < report.size(); i++) {
            int current = report.get(i);
            int step = current - previous;

            // Check the trend of the report
            switch (type) {
                case INITIAL:
                    if ( step > 0 ) {
                        type = ReportTrend.INCREASING;
                    } else if ( step < 0 ) {
                        type = ReportTrend.DECREASING;
                    } else {
                        return false;
                    }
                    break;
                case INCREASING:
                    if ( step < 0 ) {
                        return false;
                    }
                    break;
                case DECREASING:
                    if ( step > 0 ) {
                        return false;
                    }
                    break;
            }

            // Check magnitude of change
            if ( Math.abs(step) < 1 || Math.abs(step) > 4 ) {
                return false;
            }

            if ( previous == current ) {
                return false;
            }

            previous = current;
        }
        return true;
    }

    public enum ReportTrend {
        INITIAL, INCREASING, DECREASING
    }
}
