package org.aoc2024;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    Solution solution = new Solution();

    @Test
    void shouldReturn143_forPuzzleTest_Part1() {
        Integer expectedResult = 143;
        String fileName = "puzzleTest.txt";
        solution.getSolution(fileName, 1);
        Integer actualResult = solution.getSumOfMiddles();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturn123_forPuzzleTest_Part1() {
        Integer expectedResult = 123;
        String fileName = "puzzleTest.txt";
        solution.getSolution(fileName, 2);
        Integer actualResult = solution.getSumOfMiddles();
        assertEquals(expectedResult, actualResult);
    }

}