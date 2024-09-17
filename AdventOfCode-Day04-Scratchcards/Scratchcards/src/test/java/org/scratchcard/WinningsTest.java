package org.scratchcard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WinningsTest {
    Winnings winnings = new Winnings();

    @Test
    void shouldGet13ForPuzzleExample()  {
        int startSum = Winnings.totalWinnings;
        winnings.readFromFile("puzzleExample.txt");
        int endSum = Winnings.totalWinnings;
        int actualResultPart1 = endSum - startSum;
        int actualResultPart2 = Winnings.getTotalNumberOfCards();
        int expectedResultPart1 = 13;
        int expectedResultPart2 = 30;
        assertEquals(expectedResultPart1, actualResultPart1);
        assertEquals(expectedResultPart2, actualResultPart2);
    }

}