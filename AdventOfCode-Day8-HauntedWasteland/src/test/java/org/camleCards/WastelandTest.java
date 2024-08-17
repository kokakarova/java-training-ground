package org.camleCards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WastelandTest {
    Wasteland wasteland = new Wasteland();
    @Test
    void shouldReturn_2_PuzzleExample1() {
        wasteland.readFromFile("puzzleExample1.txt");
        int expected = 2;
        int actual = wasteland.findZZZ();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturn_6_PuzzleExample2() {
        wasteland.readFromFile("puzzleExample2.txt");
        int expected = 6;
        int actual = wasteland.findZZZ();
        System.out.println("actual = " + actual);
        assertEquals(expected, actual);
    }

}