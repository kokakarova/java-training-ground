package org.aoc2024;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Data
public class FileReader {

    Navigator nav = new Navigator();
    public StartingGrid getPatrolGrid(String fileName, int rows, int cols) {
        char[][] grid = new char[rows][cols];
        int[] startPosition = new int[2];
        try (InputStream file = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            int rowNum = 0;
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                char[] chars = line.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    if (chars[i] == '^') {
                        startPosition[0] = rowNum;
                        startPosition[1] = i;
                        grid[rowNum][i] = '.';
                    } else {
                        grid[rowNum][i] = chars[i];
                    }
                }
                rowNum++;
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        int[] nextPosition = nav.updateNextStep(startPosition, 0);
        return new StartingGrid(grid, startPosition, nextPosition);
    }
}
