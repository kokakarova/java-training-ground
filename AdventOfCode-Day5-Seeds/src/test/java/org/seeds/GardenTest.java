package org.seeds;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GardenTest {
    Garden garden = new Garden();

    @Test
    void shouldReturn35ForPuzzleExample() {
        String fileName = "puzzleExample.txt";
        int expected = 35;
        garden.readFromFile(fileName);
        assertEquals(expected, 30);
    }

}