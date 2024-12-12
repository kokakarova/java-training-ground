package org.aoc2024;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {
    Solution solution = new Solution();

    @Test
    void shouldReturn41_ForPuzzleTest_Part1() {
        int expectedResult = 41;
        String fileName = "puzzleTest.txt";
        int rows = 10;
        int cols = 10;
        char[][] grid = solution.getPatrolGrid(fileName, rows, cols);
        solution.getSolution(grid);
        assertEquals(expectedResult, solution.stepsCount);
    }
    @Test
    void shouldReturn1_ForInputMovement0() {
        int expectedResult = 1;
        int movement = 0;
        assertEquals(expectedResult, solution.updateMovementDirection(movement));
    }


}