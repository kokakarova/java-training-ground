package org.aoc2024;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    Solution solution = new Solution();

    @Test
    void shouldGet161_forPuzzleTest() {
        String fileName = "puzzleTest.txt";
        solution.getSolution(fileName);
        int actualResult = solution.getSumOfMultiples();
        assertEquals(161, actualResult);
    }

}