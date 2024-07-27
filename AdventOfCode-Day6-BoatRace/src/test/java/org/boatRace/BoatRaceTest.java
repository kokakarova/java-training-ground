package org.boatRace;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class BoatRaceTest {

    @Test
    void shouldReturn288ForPuzzleExample() {
        BoatRace boatRace = new BoatRace(List.of(7, 15, 30), List.of(9, 40, 200));
        int expectedResult = 288;
        assertEquals(expectedResult, boatRace.getFinalResult());
    }
}