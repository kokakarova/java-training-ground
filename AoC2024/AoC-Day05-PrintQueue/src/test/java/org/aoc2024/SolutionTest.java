package org.aoc2024;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    Solution solution = new Solution();

    @Test
    void shouldReturn143_forPuzzleTest_Part1() {
        int exprectedResult = 143;
        String fileName = "puzzleTest.txt";
        solution.getSolution(fileName);
        System.out.println(solution.getRulesMap().toString());
    }

}