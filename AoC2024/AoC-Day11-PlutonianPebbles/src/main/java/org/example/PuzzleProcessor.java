package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PuzzleProcessor {

    public static List<String> stonesList = new ArrayList<>();
    public static Map<String, Long> stonesMap = new HashMap<>();
    public static final String ZERO = "ZERO";
    public static final String EVEN_NUMBER_OF_DIGITS = "EVEN_NUMBER_OF_DIGITS";
    public static final String MULTIPLY_BY_2024 = "MULTIPLY_BY_2024";

    public void readFromInputFile(String fileName) {

        try (InputStream file = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                StringBuilder stoneBuilder = new StringBuilder();
                for (int i = 0; i < line.length(); i++) {
                    if (Character.isDigit(line.charAt(i))) {
                        stoneBuilder.append(line.charAt(i));
                        if (i == line.length() - 1) {
                            stonesList.add(String.valueOf(stoneBuilder));
                            stonesMap.put(String.valueOf(stoneBuilder), stonesMap.getOrDefault(String.valueOf(stoneBuilder), 0L) + 1);
                        }
                    } else {
                        stonesMap.put(String.valueOf(stoneBuilder), stonesMap.getOrDefault(String.valueOf(stoneBuilder), 0L) + 1);
                        stonesList.add(String.valueOf(stoneBuilder));
                        stoneBuilder = new StringBuilder();
                    }
                }
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        System.out.println("Puzzle file processed!");
    }

    public long getFinalTotalStones(int blinks) {
        long totalStones = 0L;
        for (String stone : stonesList) {
            totalStones += blinkAtTheStonesRecursive(blinks, stone);
        }
        return totalStones;
    }

    private String getSplitStoneValue(String splitStone) {
        if (splitStone.length() == 1) {
            return splitStone;
        }
        if (splitStone.charAt(0) == '0') {
            return getSplitStoneValue(splitStone.substring(1));
        }
        return splitStone;
    }

    private String getStoneRule(String stone) {
        if (stone.equalsIgnoreCase("0")) {
            return ZERO;
        }
        if (stone.length() % 2 == 0) {
            return EVEN_NUMBER_OF_DIGITS;
        }
        return MULTIPLY_BY_2024;
    }

    public long getStonesCount(Map<String, Long> stones) {
        long count = 0;
        for (Map.Entry<String, Long> ent : stones.entrySet()) {
            count += ent.getValue();
        }
        return count;
    }

    public long blinkAtTheStonesNonRecursiveFaster(int blinks) {
        long multiplier = 2024L;
//        long finalStonesCount = getStonesCount(stonesMap);
        for (int i = 0; i < blinks; i++) {
            Map<String, Long> newStones = new HashMap<>();
            for (Map.Entry<String, Long> currentStoneView : stonesMap.entrySet()) {
//                if (i == blinks - 1) {
//                    System.out.println("currentStoneView = " + currentStoneView);
//                }
                String observedEngravingKey = currentStoneView.getKey();
                Long observedEngravingValue = currentStoneView.getValue();
                String rule = getStoneRule(currentStoneView.getKey());
                switch (rule) {
                    case ZERO ->
                            newStones.put("1", (newStones.getOrDefault(observedEngravingKey, 0L) + 1) * observedEngravingValue);
//                            stonesList.add("1");
                    case EVEN_NUMBER_OF_DIGITS -> {
                        String firstSubStone = getSplitStoneValue(observedEngravingKey.substring(0, observedEngravingKey.length() / 2));
                        newStones.put(firstSubStone, (newStones.getOrDefault(firstSubStone, 0L) + 1) * observedEngravingValue);
                        String secondSubStone = getSplitStoneValue(observedEngravingKey.substring(observedEngravingKey.length() / 2));
                        newStones.put(secondSubStone, (newStones.getOrDefault(secondSubStone, 0L) + 1) * observedEngravingValue);
//                        stonesList.add(getSplitStoneValue(observedStone.substring(0, observedStoneLength / 2)));
//                        stonesList.add(getSplitStoneValue(observedStone.substring(observedStoneLength / 2)));
                    }
                    default -> {
                        long multiplied = Long.parseLong(observedEngravingKey, 10) * multiplier;
//                        stonesList.add(String.valueOf(multiplied));
                        newStones.put(String.valueOf(multiplied), (newStones.getOrDefault(String.valueOf(multiplied), 0L) + 1) * observedEngravingValue);
                    }
                }
            }
//            finalStonesCount += getStonesCount(newStones);
            System.out.println("stonesMap, size = " + stonesMap.size());
            for (Map.Entry ent : stonesMap.entrySet()) {
                System.out.println("ent = " + ent);
            }
            stonesMap = newStones;
        }
        System.out.println("Final Stones, size = " + stonesMap.size());
        for (Map.Entry ent : stonesMap.entrySet()) {
            System.out.println("ent = " + ent);
        }
        return getStonesCount(stonesMap);
    }

    public void blinkAtTheStonesNonRecursive(int blinks) {
        long multiplier = 2024L;
        for (int i = 0; i < blinks; i++) {
            List<String> currentStones = new ArrayList<>(stonesList);
            stonesList.clear();
            for (String observedStone : currentStones) {
                int observedStoneLength = observedStone.length();
                String rule = getStoneRule(observedStone);
                switch (rule) {
                    case ZERO -> stonesList.add("1");
                    case EVEN_NUMBER_OF_DIGITS -> {
                        stonesList.add(getSplitStoneValue(observedStone.substring(0, observedStoneLength / 2)));
                        stonesList.add(getSplitStoneValue(observedStone.substring(observedStoneLength / 2)));
                    }
                    default -> {
                        long multiplied = Long.parseLong(observedStone, 10) * multiplier;
                        stonesList.add(String.valueOf(multiplied));
                    }
                }
            }
        }
    }

    public long blinkAtTheStonesRecursive(int blinks, String stone) {
        if (blinks == 0) {
            return 1;
        }
        long stonesCount = 0;
        long multiplier = 2024L;
        int observedStoneLength = stone.length();
        String rule = getStoneRule(stone);
        int updatedBlink = blinks - 1;
        switch (rule) {
            case ZERO -> {
                stonesCount += blinkAtTheStonesRecursive(updatedBlink, "1");
            }
            case EVEN_NUMBER_OF_DIGITS -> {
                stonesCount += blinkAtTheStonesRecursive(updatedBlink, getSplitStoneValue(stone.substring(0, observedStoneLength / 2)));
                stonesCount += blinkAtTheStonesRecursive(updatedBlink, getSplitStoneValue(stone.substring(observedStoneLength / 2)));
            }
            default -> {
                long multiplied = Long.parseLong(stone, 10) * multiplier;
                stonesCount += blinkAtTheStonesRecursive(updatedBlink, String.valueOf(multiplied));
            }
        }
        return stonesCount;
    }

}
