package org.aoc2024;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {
    Solution solution = new Solution();

    @Test
    void shouldReturn18_forPuzzleText_Part1() {
        int rows = 10;
        int cols = 10;
        String fileName = "puzzleTest.txt";
        String word = "XMAS";
        char[][] puzzleGrid = solution.getPuzzleGridFromInput(fileName, rows, cols);
        solution.searchWord(puzzleGrid, word);
        int actualResult = Solution.matchedWordsCount;
        assertEquals(18, actualResult);
    }

}