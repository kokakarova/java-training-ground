package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleProcessorTest {

    PuzzleProcessor pp = new PuzzleProcessor();
    String fileName = "puzzleTest1.txt";

    @BeforeEach
    void setUp() {
        PuzzleProcessor.stonesList.clear();
    }

    @Test
    void shouldReturn_StringLIst_OfSize2() {
        List<String> expectedList = List.of("125", "17");
        pp.readFromInputFile(fileName);
        List<String> actualResult = PuzzleProcessor.stonesList;
        assertEquals(expectedList.size(), actualResult.size());
        assertEquals(expectedList.getFirst(), actualResult.getFirst());
        assertEquals(expectedList.get(1), actualResult.get(1));
    }

    @Test
    void shouldHave_22Stones_After_2Blinks_Part1() {
        List<String> expectedList = List.of("253", "0", "2024", "14168");
        int blinks = 2;
        pp.readFromInputFile(fileName);
        pp.blinkAtTheStones(blinks);
        List<String> actualResult = PuzzleProcessor.stonesList;
        assertEquals(expectedList.size(), actualResult.size());
        assertEquals(expectedList.getFirst(), actualResult.getFirst());
        assertEquals(expectedList.get(1), actualResult.get(1));
    }

    @Test
    void shouldHave_22Stones_After_6Blinks_Part1() {
        List<String> expectedList = List.of("2097446912", "14168", "4048", "2", "0", "2", "4", "40", "48", "2024", "40", "48", "80", "96", "2", "8", "6", "7", "6", "0", "3", "2");
        int blinks = 6;
        pp.readFromInputFile(fileName);
        pp.blinkAtTheStones(blinks);
        List<String> actualResult = PuzzleProcessor.stonesList;
        assertEquals(expectedList.size(), actualResult.size());
        assertEquals(expectedList.getFirst(), actualResult.getFirst());
        assertEquals(expectedList.get(1), actualResult.get(1));
    }

    @Test
    void shouldHave_55312Stones_After_25Blinks_Part1() {
        int expectedStones = 55312;
        int blinks = 25;
        pp.readFromInputFile(fileName);
        pp.blinkAtTheStones(blinks);
        int actualStones = PuzzleProcessor.stonesList.size();
        assertEquals(expectedStones, actualStones);
    }
}