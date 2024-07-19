package org.scratchcard;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class WinningsTest {
    Winnings winnings = new Winnings();

    @Test
    void shouldGet13ForPuzzleExample() throws IOException {
        int startSum = Winnings.totalWinnings;
        winnings.readFromFile("puzzleExample.txt", "winnings");
        int endSum = Winnings.totalWinnings;
        int actualResult = endSum - startSum;
        int expectedResult = 13;
        assertEquals(expectedResult, actualResult);
    }

}