package org.aoc2024;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {
    Solution solution = new Solution();

    @Test
    void shouldReturn18_forPuzzleTest1_Part1() {
        int rows = 10;
        int cols = 10;
        String fileName = "puzzleTest1.txt";
        String word = "XMAS";
        char[][] puzzleGrid = solution.getPuzzleGridFromInput(fileName, rows, cols);
        solution.searchWord(puzzleGrid, word, 1);
        int actualResult = Solution.matchedWordsCount;
        assertEquals(18, actualResult);
    }

    @Test
    void shouldReturn9_forPuzzleTest2_Part2() {
        Solution.matchedWordsCount = 0;
        int rows = 10;
        int cols = 10;
        String fileName = "puzzleTest2.txt";
        String word = "MAS";
        char[][] puzzleGrid = solution.getPuzzleGridFromInput(fileName, rows, cols);
        solution.searchWord(puzzleGrid, word, 2);
        int actualResult = Solution.matchedWordsCount / 2;
        assertEquals(9, actualResult);
    }

    @Test
    void shouldReturn1_forPuzzleTest3_Part2() {
        Solution.matchedWordsCount = 0;
        int rows = 3;
        int cols = 4;
        String fileName = "puzzleTest3.txt";
        String word = "MAS";
        char[][] puzzleGrid = solution.getPuzzleGridFromInput(fileName, rows, cols);
        solution.searchWord(puzzleGrid, word, 2);
        int actualResult = Solution.matchedWordsCount / 2;
        assertEquals(1, actualResult);
    }

    @Test
    void shouldReturn3_forPuzzleTest4_Part2() {
        Solution.matchedWordsCount = 0;
        int rows = 5;
        int cols = 14;
        String fileName = "puzzleTest4.txt";
        String word = "MAS";
        char[][] puzzleGrid = solution.getPuzzleGridFromInput(fileName, rows, cols);
        solution.searchWord(puzzleGrid, word, 2);
        int actualResult = Solution.matchedWordsCount / 2;
        assertEquals(3, actualResult);
    }

    @Test
    void shouldReturn4_forPuzzleTest5_Part2() {
        Solution.matchedWordsCount = 0;
        int rows = 15;
        int cols = 4;
        String fileName = "puzzleTest5.txt";
        String word = "MAS";
        char[][] puzzleGrid = solution.getPuzzleGridFromInput(fileName, rows, cols);
        System.out.println("puzzleGrid rows = " + puzzleGrid.length);
        System.out.println("puzzleGrid columns = " + puzzleGrid[0].length);
        solution.searchWord(puzzleGrid, word, 2);
        int actualResult = Solution.matchedWordsCount / 2;
        assertEquals(4, actualResult);
    }

}