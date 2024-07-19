package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BrokenGearsTest {
    BrokenGears bg = new BrokenGears();

    @Test
    void shouldReturn_600_forPuzzlePart2HandleGearSymbol() throws IOException {
        int initialSumVal = (int) bg.getTotalSum();
        String fileName = "puzzlePart2HandleGearSymbol.txt";
        bg.readFromFile(fileName);
        int endSumVal = (int) bg.getTotalSum();
        int expectedResult = 600;
        int actualResult = endSumVal - initialSumVal;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturn_1000_forPuzzlePart2HandleNumbers() throws IOException {
        int initialSumVal = (int) bg.getTotalSum();
        String fileName = "puzzlePart2HandleNumbers.txt";
        bg.readFromFile(fileName);
        int endSumVal = (int) bg.getTotalSum();
        int expectedResult = 1000;
        int actualResult = endSumVal - initialSumVal;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturn_467835_forPuzzleExample() throws IOException {
        int initialSumVal = (int) bg.getTotalSum();
        String fileName = "puzzleExample.txt";
        bg.readFromFile(fileName);
        int endSumVal = (int) bg.getTotalSum();
        int expectedResult = 467835;
        int actualResult = endSumVal - initialSumVal;
        assertEquals(expectedResult, actualResult);
    }

}