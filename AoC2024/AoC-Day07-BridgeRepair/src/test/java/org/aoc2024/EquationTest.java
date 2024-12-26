package org.aoc2024;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EquationTest {

    Equation equation = new Equation();
    InputProcessor inputProcessor = new InputProcessor();

    @Test
    void shouldAddUpTo_3749_ForPuzzleTest_Part1() {
        String fileName = "puzzleTest.txt";
        inputProcessor.readEquationsFromInput(fileName);
        int expected = 3749;
        assertEquals(expected, Equation.validEquations);
    }

}