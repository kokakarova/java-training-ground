package org.camleCards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CamelCardsTest {
    CamelCards c = new CamelCards();
    String fileName = "puzzleExample.txt";

//    @Test
//    void shouldReturn6440ForPuzzleExample() {
//        int startMapSize = c.getHandsAndBids().size();
//        long expected = 6440;
//        int endMapSize = c.getHandsAndBids().size();
//    }
    @Test
    void shouldReturnMapSize5ForPuzzleExample() {
        int startMapSize = c.getHandsAndBids().size();
        long expected = 5;
        c.readFromFile(fileName);
        int endMapSize = c.getHandsAndBids().size();
        assertEquals(expected, endMapSize - startMapSize);
    }

    @Test
    void shouldContainKeyKK677AndValue28ForPuzzleExample() {
        String expectedKey = "KK677";
        Integer expectedValue = 28;
        c.readFromFile(fileName);
        assertTrue(c.getHandsAndBids().containsKey(expectedKey));
        assertEquals(expectedValue, c.getHandsAndBids().get(expectedKey));
    }

}