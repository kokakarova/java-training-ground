package org.aoc2024;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


import java.util.*;

import static org.aoc2024.PuzzleProcessor.frequenciesAntennas;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PuzzleProcessorTest {

    PuzzleProcessor pp = new PuzzleProcessor(11);
    String fileName = "puzzleTest.txt";

    @AfterEach
    void tearDown() {
        frequenciesAntennas = new HashMap<>();
    }

    @Test
    void shouldCreateMap_With2Keys() {
        pp.readAntennasFromInput(fileName);
        int expectedKeys = 2;
        assertEquals(expectedKeys, PuzzleProcessor.frequenciesAntennas.keySet().size());
    }

    @Test
    void shouldHave_ListOf4_For0_And_ListOf3_ForA() {
        pp.readAntennasFromInput(fileName);
        int expectedListSizeFor0 = 4;
        int expectedListSizeForA = 3;
        assertEquals(expectedListSizeFor0, PuzzleProcessor.frequenciesAntennas.get('0').size());
        assertEquals(expectedListSizeForA, PuzzleProcessor.frequenciesAntennas.get('A').size());
    }

    @Test
    void shouldHave_1And8_AsFirstCoors_For0() {
        pp.readAntennasFromInput(fileName);
        int[] expectedFirstCoordsFor0 = new int[]{1, 8};
        assertEquals(expectedFirstCoordsFor0[0], PuzzleProcessor.frequenciesAntennas.get('0').getFirst()[0]);
        assertEquals(expectedFirstCoordsFor0[1], PuzzleProcessor.frequenciesAntennas.get('0').getFirst()[1]);
    }

    @Test
    void shouldHave_5And6_AsFirstCoors_ForA() {
        pp.readAntennasFromInput(fileName);
        int[] expectedFirstCoordsForA = new int[]{5, 6};
        assertEquals(expectedFirstCoordsForA[0], PuzzleProcessor.frequenciesAntennas.get('A').getFirst()[0]);
        assertEquals(expectedFirstCoordsForA[1], PuzzleProcessor.frequenciesAntennas.get('A').getFirst()[1]);
    }

    @Test
    void shouldGetListSize14_forPuzzleTest_Part1() {
        pp.readAntennasFromInput(fileName);
        int expectedSize = 14;
        assertEquals(expectedSize, pp.findAntinodesForAllAntennas(1).size());
    }

    @Test
    void shouldGetListSize34_forPuzzleTest_Part2() {
        pp.readAntennasFromInput(fileName);
        int expectedSize = 34;
        Set<String> antinodesString = pp.findAntinodesForAllAntennas(2);
        int countUnmatchedAntennas = pp.addUnmatchedAntennaPositions(antinodesString);
        assertEquals(expectedSize, antinodesString.size() + countUnmatchedAntennas);
    }

}