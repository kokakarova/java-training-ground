package org.aoc2024;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquationTest {

    Equation equation = new Equation();
    FileReader fileReader = new FileReader();

    @Test
    void testTest() {
        String fileName = "puzzleTest.txt";
        fileReader.readEquationsFromInput(fileName);
    }

}