package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BrokenGearsTest {
    BrokenGears bg = new BrokenGears();

    @Test
    void shouldReturn_600_forPuzzlePart2Simple() throws IOException {
        int initialSumVal = (int) bg.getTotalSum();
        String fileName = "puzzlePart2Simple.txt";
        bg.readFromFile(fileName);
        int endSumVal = (int) bg.getTotalSum();
        int expectedResult = 600;
        int actualResult = endSumVal - initialSumVal;
        assertEquals(expectedResult, actualResult);
    }
}