package org.aoc2024;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int rows = 140;
        int cols = 140;
        String fileName = "puzzle.txt";
        String word ="XMAS";
        char[][] puzzleGrid = solution.getPuzzleGridFromInput(fileName, rows, cols);
        solution.searchWord(puzzleGrid,word);
        System.out.println("PART_1 solution: " + Solution.matchedWordsCount);
    }
}