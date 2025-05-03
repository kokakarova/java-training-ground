package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleProcessorTest {

    PuzzleProcessor pp = new PuzzleProcessor();
    String fileName = "puzzleTest1.txt";

    @BeforeEach
    void setUp() {
        PuzzleProcessor.stonesList.clear();
    }

    @Test
    void shouldReturn_StringList_OfSize2() {
        List<String> expectedList = List.of("125", "17");
        pp.readFromInputFile(fileName);
        List<String> actualResult = PuzzleProcessor.stonesList;
        assertEquals(expectedList.size(), actualResult.size());
        assertEquals(expectedList.getFirst(), actualResult.getFirst());
        assertEquals(expectedList.get(1), actualResult.get(1));
    }

    @Test
    void shouldReturn_Map_OfSize2() {
        Map<String, Long> expectedList = Map.of("125", 1L, "17", 1L);
        pp.readFromInputFile(fileName);
        Map<String, Long> actualResult = PuzzleProcessor.stonesMap;
        System.out.println("actual result map");
        for (Map.Entry<String, Long> ent: actualResult.entrySet()) {
            System.out.println("entrySet = " + ent.getKey() + ", " + ent.getValue());
        }
        assertEquals(expectedList.size(), actualResult.size());
    }

    @Test
    void shouldHave_22Stones_After_2Blinks_Part1() {
        List<String> expectedList = List.of("253", "0", "2024", "14168");
        int blinks = 2;
        pp.readFromInputFile(fileName);
        pp.blinkAtTheStonesNonRecursive(blinks);
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
        pp.blinkAtTheStonesNonRecursive(blinks);
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
        pp.blinkAtTheStonesNonRecursive(blinks);
        int actualStones = PuzzleProcessor.stonesList.size();
        assertEquals(expectedStones, actualStones);
    }

    @Test
    void shouldHave_4Stones_After_2Blinks_Recursive() {
        List<String> expectedList = List.of("253", "0", "2024", "14168");
        int expectedNumberOfStones = expectedList.size();
        int blinks = 2;
        pp.readFromInputFile(fileName);
        long actualNumberOfStones = pp.getFinalTotalStones(blinks);
        assertEquals(expectedNumberOfStones, actualNumberOfStones);
    }

    @Test
    void shouldHave_22Stones_After_6Blinks_Recursive() {
        List<String> expectedList = List.of("2097446912", "14168", "4048", "2", "0", "2", "4", "40", "48", "2024", "40", "48", "80", "96", "2", "8", "6", "7", "6", "0", "3", "2");
        int expectedNumberOfStones = expectedList.size();
        int blinks = 6;
        pp.readFromInputFile(fileName);
        long actualNumberOfStones = pp.getFinalTotalStones(blinks);
        assertEquals(expectedNumberOfStones, actualNumberOfStones);
    }


    @Test
    void shouldHave_4Stones_After_2Blinks_Faster() {
//        List<String> expectedList = List.of("253", "0", "2024", "14168");
        long expectedNumberOfStones = 4L;
        int blinks = 2;
        pp.readFromInputFile(fileName);
        long actualNumberOfStones = pp.blinkAtTheStonesNonRecursiveFaster(blinks);
        assertEquals(expectedNumberOfStones, actualNumberOfStones);
    }
    
    @Test
    void shouldHave_22Stones_After_6Blinks_NonRecursive_Faster() {
//        List<String> expectedList = List.of("2097446912", "14168", "4048", "2", "0", "2", "4", "40", "48", "2024", "40", "48", "80", "96", "2", "8", "6", "7", "6", "0", "3", "2");
        long expectedNumberOfStones = 22L;
        int blinks = 6;
        pp.readFromInputFile(fileName);
        long actualNumberOfStones = pp.blinkAtTheStonesNonRecursiveFaster(blinks);
        assertEquals(expectedNumberOfStones, actualNumberOfStones);
    }
}