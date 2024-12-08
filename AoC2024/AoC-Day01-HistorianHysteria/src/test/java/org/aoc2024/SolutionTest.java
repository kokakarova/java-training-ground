package org.aoc2024;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    void shouldReturn11_ForPuzzlePart1() {
        String fileName = "puzzleTest.txt";
        assertEquals(11, solution.getSolution(fileName, 1));
    }

    @Test
    void shouldReturn31_ForPuzzlePart2() {
        String fileName = "puzzleTest.txt";
        assertEquals(31, solution.getSolution(fileName, 2));
    }


}