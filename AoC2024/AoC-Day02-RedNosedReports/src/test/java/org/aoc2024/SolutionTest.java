package org.aoc2024;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    void shouldReturn2_forPuzzleTest_ForPart1() {
        String fileName = "puzzleTest.txt";
        solution.getSolution(fileName, 1);
        assertEquals(2, solution.getSafeReportsCount());
    }

    @Test
    void shouldReturn4_forPuzzleTest_ForPart2() {
        String fileName = "puzzleTest.txt";
        solution.getSolution(fileName, 2);
        assertEquals(4, solution.getSafeReportsCount());
    }
}