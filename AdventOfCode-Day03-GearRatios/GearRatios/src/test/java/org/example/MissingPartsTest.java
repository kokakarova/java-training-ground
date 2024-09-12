package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MissingPartsTest {

    CalculateMissingParts missingParts = new CalculateMissingParts();
    Numbers numbers = new Numbers();
    SpecialChars specialChars = new SpecialChars();

    @Test
    void shouldGet12203ForTotalSum() throws IOException {
        int initialSumVal = (int) missingParts.getTotalSum();
        String fileName = "puzzleFileTest.txt";
        missingParts.readFromFile(fileName);
        int endSumVal = (int) missingParts.getTotalSum();
        int expectedResult = 12203;
        int actualResult = endSumVal - initialSumVal;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldGet794ForTotalSum() throws IOException {
        int initialSumVal = (int) missingParts.getTotalSum();
        String fileName = "puzzleFileTestSpecialCharInPrevLine.txt";
        missingParts.readFromFile(fileName);
        int endSumVal = (int) missingParts.getTotalSum();
        int expectedResult = 794;
        int actualResult = endSumVal - initialSumVal;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldGet2573ForTotalSum() throws IOException {
        int initialSumVal = (int) missingParts.getTotalSum();
        String fileName = "puzzleFileTestSpecialChar.txt";
        missingParts.readFromFile(fileName);
        int endSumVal = (int) missingParts.getTotalSum();
        int expectedResult = 5206;
        int actualResult = endSumVal - initialSumVal;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldGet100ForTotalSum() throws IOException {
        int initialSumVal = (int) missingParts.getTotalSum();
        String fileName = "puzzleFileTestSpecialCharBeforeNum.txt";
        missingParts.readFromFile(fileName);
        int endSumVal = (int) missingParts.getTotalSum();
        int expectedResult = 100;
        int actualResult = endSumVal - initialSumVal;
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void shouldGet434ForTotalSum() throws IOException {
        int initialSumVal = (int) missingParts.getTotalSum();
        String fileName = "puzzleFileTestSpecialCharAfterNum.txt";
        missingParts.readFromFile(fileName);
        int endSumVal = (int) missingParts.getTotalSum();
        int expectedResult = 434;
        int actualResult = endSumVal - initialSumVal;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldGet6672ForTotalSum() throws IOException {
        int initialSumVal = (int) missingParts.getTotalSum();
        String fileName = "puzzleFileTest-1.txt";
        missingParts.readFromFile(fileName);
        int endSumVal = (int) missingParts.getTotalSum();
        int expectedResult = 6672;
        int actualResult = endSumVal - initialSumVal;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldGet7104ForTotalSum() throws IOException {
        int initialSumVal = (int) missingParts.getTotalSum();
        String fileName = "puzzleFileTest-2.txt";
        missingParts.readFromFile(fileName);
        int endSumVal = (int) missingParts.getTotalSum();
        int expectedResult = 7104;
        int actualResult = endSumVal - initialSumVal;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldGet4361ForTotalSum() throws IOException {
        int initialSumVal = (int) missingParts.getTotalSum();
        String fileName = "puzzleExample.txt";
        missingParts.readFromFile(fileName);
        int endSumVal = (int) missingParts.getTotalSum();
        int expectedResult = 4361;
        int actualResult = endSumVal - initialSumVal;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldGet467835ForTotalSum() throws IOException {
        int initialSumVal = (int) missingParts.getTotalSum();
        String fileName = "puzzleExample.txt";
        missingParts.readFromFile(fileName);
        int endSumVal = (int) missingParts.getTotalSum();
        int expectedResult = 467835;
        int actualResult = endSumVal - initialSumVal;
        assertEquals(expectedResult, actualResult);
    }
}