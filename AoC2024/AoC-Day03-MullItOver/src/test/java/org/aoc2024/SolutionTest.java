package org.aoc2024;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    Solution solution = new Solution();

    @Test
    void shouldGet161_forPuzzleTest_Part1() {
        String fileName = "puzzleTest1.txt";
        solution.getSolution(fileName, 1);
        int actualResult = solution.getSumOfMultiples();
        assertEquals(161, actualResult);
    }
    @Test
    void shouldGet48_forPuzzleTest_Part2() {
        String fileName = "puzzleTest2.txt";
        solution.getSolution(fileName, 2);
        int actualResult = solution.getSumOfMultiples();
        assertEquals(48, actualResult);
    }

}