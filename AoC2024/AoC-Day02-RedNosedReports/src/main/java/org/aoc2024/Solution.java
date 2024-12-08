package org.aoc2024;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Solution {

    private int safeReportsCount = 0;

    public void getSolution(String fileName, int partNumber) {
        try (InputStream file = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if (partNumber != 1 && partNumber != 2) {
                    throw new IllegalArgumentException("Invalid part number: " + partNumber + ". Part number can only be 1 or 2");
                }
                if (partNumber == 1) {
                    int[] levels = stringToIntArray(line);
                    if (isSafePart1(levels)) {
                        safeReportsCount++;
                    }
                } else {
                    List<Integer> levels = new ArrayList<>(stringToIntList(line));
                    if (isSafePart2(levels)) {
                        safeReportsCount++;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isSafePart2(List<Integer> levels) {
        int unsafeLevel = checkUnsafeLevel(levels);
        if (unsafeLevel == -1) {
            return true;
        } else {
            for (int i = 0; i < levels.size(); i++) {
                List<Integer> copyOfLevels = new ArrayList<>(levels);
                copyOfLevels = removeElementFromList(copyOfLevels, i);
                unsafeLevel = checkUnsafeLevel(copyOfLevels);
                if (unsafeLevel == -1) {
                    return true;
                }
            }
            return false;
        }
    }

    private List<Integer> removeElementFromList(List<Integer> levelsList, int indexToRemove) {
        if (indexToRemove == 0) {
            return levelsList.subList(1, levelsList.size());
        }
        if (indexToRemove >= levelsList.size()) {
            return levelsList;
        }
        levelsList.remove(indexToRemove);
        return levelsList;
    }

    private int checkUnsafeLevel(List<Integer> levels) {
        boolean increasingOrder = levels.get(0) < levels.get(1);
        for (int i = 1; i < levels.size(); i++) {
            int levelDifference = levels.get(i) - levels.get(i - 1);
            if (Math.abs(levelDifference) > 3 || levelDifference == 0) {
                return i;
            }
            if (increasingOrder != levels.get(i - 1) < levels.get(i)) {
                return i;
            }
        }
        return -1;
    }

    private List<Integer> stringToIntList(String line) {
        String[] levelsString = line.split(" ");
        return Arrays.stream(levelsString).map(Integer::parseInt).toList();
    }

    private int[] stringToIntArray(String line) {
        String[] levelsString = line.split(" ");
        int[] levels = new int[levelsString.length];
        for (int i = 0; i < levelsString.length; i++) {
            levels[i] = Integer.parseInt(levelsString[i]);
        }
        return levels;
    }

    private boolean isSafePart1(int[] levels) {
        boolean increasingOrder = levels[0] < levels[1];
        for (int i = 1; i < levels.length; i++) {
            int levelDifference = levels[i] - levels[i - 1];
            if (Math.abs(levelDifference) > 3 || levelDifference == 0) {
                return false;
            }
            if (increasingOrder != levels[i - 1] < levels[i]) {
                return false;
            }
        }
        return true;
    }
}
