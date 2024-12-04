package com.amcclain.advent2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ListDistance {
    public static void main(String[] args) throws IOException {
        Tuple<List<Integer>, List<Integer>> lists = readInput();
        System.out.println("The list distance is : " + calculateDistance(lists.first(), lists.second()));
        System.out.println("The similarity score is : " + calculateSimilarity(lists.first(), lists.second()));
    }

    public static Tuple<List<Integer>, List<Integer>> readInput() throws IOException {
        List<Integer> set1 = new ArrayList<>();
        List<Integer> set2 = new ArrayList<>();
        System.out.println(Paths.get("day-1/java/src/main/resources/input.txt").toAbsolutePath());
        Files.readAllLines(Paths.get("day-1/java/src/main/resources/input.txt")).forEach((line) -> {
            String[] pair = line.split("\s+");
            set1.add(Integer.parseInt(pair[0]));
            set2.add(Integer.parseInt(pair[1]));
        });
        return new Tuple<>(set1, set2);
    }

    public static int calculateDistance(List<Integer> list1, List<Integer> list2) {
        list1.sort(Comparator.naturalOrder());
        list2.sort(Comparator.naturalOrder());
        int totalDistace = 0;
        for (int i = 0; i < list1.size(); i++) {
            totalDistace += Math.abs(list1.get(i) - list2.get(i));
        }
        return totalDistace;
    }

    public static int calculateSimilarity(List<Integer> list1, List<Integer> list2) {
        int similarityScore = 0;
        HashMap<Integer, Integer> leftFrequency = new HashMap<>();
        HashMap<Integer, Integer> rightFrequency = new HashMap<>();

        list1.forEach((value) -> {
            if (leftFrequency.containsKey(value)) {
                leftFrequency.put(value, leftFrequency.get(value) + 1);
            } else {
                leftFrequency.put(value, 1);
            }
        });

        list2.forEach((value) -> {
            if (rightFrequency.containsKey(value)) {
                rightFrequency.put(value, rightFrequency.get(value) + 1);
            } else {
                rightFrequency.put(value, 1);
            }
        });

        for (int key : leftFrequency.keySet()) {
            if (rightFrequency.containsKey(key)) {
                similarityScore += key * leftFrequency.getOrDefault(key, 0)
                        * rightFrequency.getOrDefault(key, 0);
            }
        }
        return similarityScore;
    }

    public record Tuple<T, U>(T first, U second) {

    }
}