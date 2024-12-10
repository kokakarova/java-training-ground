package org.aoc2024;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int rows = 140;
        int cols = 140;
        String fileName = "puzzle.txt";
        String word1 ="XMAS";
        String word2 ="MAS";
        char[][] puzzleGrid = solution.getPuzzleGridFromInput(fileName, rows, cols);
//        solution.searchWord(puzzleGrid,word1, 1);
//        System.out.println("PART_1 solution: " + Solution.matchedWordsCount);
        solution.searchWord(puzzleGrid, word2, 2);
        System.out.println("PART_2 solution: " + (Solution.matchedWordsCount  / 2));
// 1694 too low
    }
}