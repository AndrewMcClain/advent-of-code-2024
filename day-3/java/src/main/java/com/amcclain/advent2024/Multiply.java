package com.amcclain.advent2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Multiply {
    public static final String REGEX = "mul\\(.*?(\\d+).*?,.*?(\\d+).*?\\)";
    public static final Pattern PATTERN = Pattern.compile(REGEX);
    public static void main(String[] args) throws IOException {
        String text = readInput();
        int total = calculateMulCommands(text);
        System.out.println("The mul operations is : " + total);
    }

    public static int calculateMulCommands(String text) {
        Matcher matcher = PATTERN.matcher(text);
        return matcher.results().mapToInt((match) -> {
            if ( PATTERN.matcher(match.group(0)).results().count() > 1) {
                return 0;
            }
            int a = Integer.parseInt(match.group(1));
            int b = Integer.parseInt(match.group(2));
            return a*b;
        }).sum();
    }

    public static String readInput() throws IOException {
        return Files.readString(Path.of("day-3/java/src/main/resources/input.txt"));
    }
}
