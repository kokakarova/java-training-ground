package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PuzzleProcessor {

    public static List<String> stonesList = new ArrayList<>();
    public static final String ZERO = "ZERO";
    public static final String EVEN_NUMBER_OF_DIGITS = "EVEN_NUMBER_OF_DIGITS";
    public static final String MULTIPLY_BY_2024 = "MULTIPLY_BY_2024";

    public void readFromInputFile(String fileName) {

        try (InputStream file = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                StringBuilder stone = new StringBuilder();
                for (int i = 0; i < line.length(); i++) {
                    if (Character.isDigit(line.charAt(i))) {
                        stone.append(line.charAt(i));
                        if (i == line.length() - 1) {
                            stonesList.add(String.valueOf(stone));
                        }
                    } else {
                        stonesList.add(String.valueOf(stone));
                        stone = new StringBuilder();
                    }
                }
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void blinkAtTheStones(int blinks) {
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

}
