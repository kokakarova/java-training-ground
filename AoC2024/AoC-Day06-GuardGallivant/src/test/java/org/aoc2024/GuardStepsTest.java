package org.aoc2024;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuardStepsTest {
    GuardSteps guardSteps = new GuardSteps();
    FileReader fileReader = new FileReader();
    String fileName = "puzzleTest.txt";

    @Test
    void shouldReturn41_ForPuzzleTest_Part1() {
        int expectedResult = 41;
        int rows = 10;
        int cols = 10;
        StartingGrid startingGrid = fileReader.getPatrolGrid(fileName, rows, cols);
        guardSteps.countGuardSteps(startingGrid, 0);
        assertEquals(expectedResult, guardSteps.stepsCount);
    }

}