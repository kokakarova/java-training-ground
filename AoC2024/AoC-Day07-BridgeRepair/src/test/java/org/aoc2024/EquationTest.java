package org.aoc2024;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EquationTest {

    Equation equation = new Equation();
    InputProcessor inputProcessor = new InputProcessor();

    @Test
    void shouldAddUpTo_3749_ForPuzzleTest_Part1() {
        String fileName = "puzzleTest.txt";
        inputProcessor.readEquationsFromInput(fileName, 1);
        int expected = 3749;
        assertEquals(expected, Equation.validEquations);
    }

    @Test
    void shouldAddUpTo_11387_ForPuzzleTest_Part2() {
        int expected = 11387;
        String fileName = "puzzleTest.txt";
        inputProcessor.readEquationsFromInput(fileName, 2);
        assertEquals(expected, Equation.validEquations);
    }

    @Test
    void test() {
        long[] arr = {0, 1, 2};
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        long[] newArr = equation.removeElementFromArr(arr, 2);
        System.out.println("newArr = " + Arrays.toString(newArr));
    }

}