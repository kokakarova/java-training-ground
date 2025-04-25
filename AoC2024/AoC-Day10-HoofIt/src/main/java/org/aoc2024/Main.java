package org.aoc2024;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PuzzleProcessor puzzleProcessor = new PuzzleProcessor();
        String fileName = "puzzle.txt";
        int dimensions = 54;
        int[][] map = puzzleProcessor.readFromInputFile(fileName, dimensions);
        List<Integer> pathsListPart1 = puzzleProcessor.getPathsPerTrailheadList(map, 1);
        long trailheadsScoreSumPart1 = pathsListPart1.stream().mapToInt(n -> n).sum();
        System.out.println("trailheadsScoreSum = " + trailheadsScoreSumPart1);
        List<Integer> pathsListPart2 = puzzleProcessor.getPathsPerTrailheadList(map, 2);
        long trailheadsScoreSumPart2 = pathsListPart2.stream().mapToInt(n -> n).sum();
        System.out.println("trailheadsScoreSum = " + trailheadsScoreSumPart2);
    }
}