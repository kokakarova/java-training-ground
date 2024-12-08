package org.aoc2024;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    void shouldPrintColumns() {
        String fileName = "puzzleTest.txt";
        assertEquals(11, solution.readFromFile(fileName));
    }
}