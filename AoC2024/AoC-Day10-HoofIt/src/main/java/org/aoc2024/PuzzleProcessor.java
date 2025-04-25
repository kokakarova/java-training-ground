package org.aoc2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class PuzzleProcessor {

    public static List<int[]> trailheadsCoordinates = new ArrayList<>();
    public static Set<String> peaksReached = new HashSet<>();
    public static List<String> uniqueTrails = new ArrayList<>();

    public int[][] readFromInputFile(String fileName, int gridDimensions) {
        int[][] map = new int[gridDimensions][gridDimensions];
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

    public List<Integer> getPathsPerTrailheadList(int[][] puzzleMap, int puzzlePart) {
        List<Integer> paths = new ArrayList<>();


        for (int[] thCoord : trailheadsCoordinates) {
            peaksReached.clear();
            uniqueTrails.clear();
            int thCoordPathsFound = searchGrid(puzzleMap, thCoord[0], thCoord[1], puzzlePart);
            if (thCoordPathsFound != 0) {
                if (puzzlePart == 1) {
                    paths.add(peaksReached.size());
                } else {
                    paths.add(uniqueTrails.size());
                }
            }
        }
        return paths;
    }

    private int searchGrid(int[][] puzzleGrid, int row, int col, int puzzlePart) {
        int[] x = new int[]{-1, 0, 1, 0};
        int[] y = new int[]{0, 1, 0, -1};
        int m = puzzleGrid.length;
        int n = puzzleGrid[0].length;
        int directions = 4;
        int pathsFound = 0;
        int peak = 9;
        int currLevel = puzzleGrid[row][col];
        for (int dir = 0; dir < directions; dir++) {
            int k;
            int currX = row + x[dir];
            int currY = col + y[dir];
            if (isOutOfBounds(currX, currY, m, n)) {
                continue;
            }
            int nextLevel = puzzleGrid[currX][currY];
            if (nextLevel == currLevel + 1 && nextLevel == peak) {
                if (puzzlePart == 1) {
                    peaksReached.add(String.valueOf(currX) + currY);
                } else {
                    uniqueTrails.add(String.valueOf(currX) + currY);
                }
                pathsFound++;
                continue;
            }
            if (nextLevel == currLevel + 1) {
                pathsFound += searchGrid(puzzleGrid, currX, currY, puzzlePart);
            }
        }
        return pathsFound;
    }

    private boolean isOutOfBounds(int currX, int currY, int m, int n) {
        return currX >= m || currX < 0 || currY >= n || currY < 0;
    }
}
