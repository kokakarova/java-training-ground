package org.aoc2024;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        PuzzleProcessor puzzleProcessor = new PuzzleProcessor();
        String fileName = "puzzle.txt";
        int dimensions = 54;
        int[][] map = puzzleProcessor.readFromInputFile(fileName, dimensions, dimensions);
        System.out.println("Arrays.deepToString(map) = " + Arrays.deepToString(map));
    }
}