package org.seeds;

import org.junit.jupiter.api.Test;
import org.seeds.enums.Part;

import static org.junit.jupiter.api.Assertions.*;

class GardenTest {
    Garden garden = new Garden();

    @Test
    void shouldReturn35ForPuzzleExample() {
        String fileName = "puzzleExample.txt";
        int expected = 35;
        long actualResult = garden.readFromFile(fileName, Part.PART_1);
        assertEquals(expected, actualResult);
    }

    @Test
    void shouldGetFinalResultForPuzzle() {
        String fileName = "puzzle.txt";
        long actualResult = garden.readFromFile(fileName, Part.PART_1);
        assertEquals(3, actualResult);
    }

}