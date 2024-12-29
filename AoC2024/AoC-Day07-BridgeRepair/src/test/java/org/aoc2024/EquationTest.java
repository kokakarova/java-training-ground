package org.aoc2024;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EquationTest {

    InputProcessor inputProcessor = new InputProcessor();

    @Test
    void shouldAddUpTo_3749_ForPuzzleTest_Part1() {
        String fileName = "puzzleTest.txt";
        Equation.validEquations = 0;
        inputProcessor.readEquationsFromInput(fileName, 1);
        int expected = 3749;
        assertEquals(expected, Equation.validEquations);
    }

    @Test
    void shouldAddUpTo_11387_ForPuzzleTest_Part2() {
        long expected = 11387;
        Equation.validEquations = 0;
        String fileName = "puzzleTest.txt";
        inputProcessor.readEquationsFromInput(fileName, 2);
        assertEquals(expected, Equation.validEquations);
    }

    @Test
    void shouldAddUpTo_7290_ForPuzzleTest2_Part2() {
        long expected = 7290;
        Equation.validEquations = 0;
        String fileName = "puzzleTest2.txt";
        inputProcessor.readEquationsFromInput(fileName, 2);
        assertEquals(expected, Equation.validEquations);
    }

    @Test
    void shouldAddUpTo_156_ForPuzzleTest3_Part2() {
        long expected = 156;
        Equation.validEquations = 0;
        String fileName = "puzzleTest3.txt";
        inputProcessor.readEquationsFromInput(fileName, 2);
        assertEquals(expected, Equation.validEquations);
    }

}