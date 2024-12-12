package org.aoc2024;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Data
public class Solution {

    int[] startPosition = new int[2];
    int stepsCount = 1;

    public char[][] getPatrolGrid(String fileName, int rows, int cols) {
        char[][] grid = new char[rows][cols];
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
        return grid;
    }

    public void getSolution(char[][] grid) {
        List<String> visitedPositions = new ArrayList<>();
        int movement = 0;
        int len = grid.length;
        boolean inScope = true;
        int[] currentPosition = startPosition;
        int[] nextStepCoords = getNextStep(currentPosition, movement);
        String visited = currentPosition[0] + "," + currentPosition[1];
        visitedPositions.add(visited);
        while (inScope) {
            if (!checkStepScope(nextStepCoords, len)) {
                inScope = false;
            } else {
                char charAtNextStep = grid[nextStepCoords[0]][nextStepCoords[1]];
                if (charAtNextStep == '.') {
                    currentPosition = nextStepCoords;
                    nextStepCoords = getNextStep(currentPosition, movement);
                    if (!visitedPositions.contains(currentPosition[0] + "," + currentPosition[1])) {
                        stepsCount++;
                        visitedPositions.add(currentPosition[0] + "," + currentPosition[1]);
                    }
                } else {
                    movement = updateMovementDirection(movement);
                    nextStepCoords = getNextStep(currentPosition, movement);
                }
            }
        }
    }

    public int updateMovementDirection(int movement) {
        return movement == 3 ? 0 : movement + 1;
    }

    private int[] getNextStep(int[] currentPosition, int movement) {
        int[][] movingDirections = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        return new int[]{currentPosition[0] + movingDirections[movement][0], currentPosition[1] + movingDirections[movement][1]};
    }

    private boolean checkStepScope(int[] nextStep, int len) {
        int xCoord = nextStep[0];
        int yCoord = nextStep[1];
        return xCoord >= 0 && xCoord < len && yCoord >= 0 && yCoord < len;
    }
}


