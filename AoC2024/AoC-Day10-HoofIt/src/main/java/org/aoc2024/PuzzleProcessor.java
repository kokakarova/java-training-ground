package org.aoc2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PuzzleProcessor {

    public static List<int[]> trailheadsCoordinates = new ArrayList<>();

    public int[][] readFromInputFile(String fileName, int rows, int cols) {
        int[][] map = new int[rows][cols];
        try (InputStream file = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            int lineNumber = 0;
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                for (int i = 0; i < line.length(); i++) {
                    int charInt = Character.getNumericValue(line.charAt(i));
                    map[lineNumber][i] = charInt;
                    if (charInt == 0) {
                        trailheadsCoordinates.add(new int[]{lineNumber, i});
                    }
                }
                lineNumber++;
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
