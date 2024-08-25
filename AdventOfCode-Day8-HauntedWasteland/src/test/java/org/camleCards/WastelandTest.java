package org.camleCards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WastelandTest {
    Wasteland wasteland = new Wasteland();
    @Test
    void shouldReturn_2_PuzzleExamplePart1_1() {
        wasteland.readFromFile("puzzleExamplePart1_1.txt", 1);
        int expected = 2;
        int actual = wasteland.findZZZ();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturn_6_PuzzleExamplePart1_2() {
        wasteland.readFromFile("puzzleExamplePart1_2.txt",1 );
        int expected = 6;
        int actual = wasteland.findZZZ();
        System.out.println("actual = " + actual);
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturn_6_PuzzleExamplePart2_1() {
        wasteland.readFromFile("puzzleExamplePart2_1.txt", 2);
        int expected = 6;
        int actual = wasteland.find10ZNodes();
        assertEquals(expected, actual);

    }

}