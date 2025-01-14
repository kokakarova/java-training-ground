package org.aoc2024;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.aoc2024.PuzzleProcessor.trailheadsCoordinates;
import static org.junit.jupiter.api.Assertions.*;

class PuzzleProcessorTest {
    PuzzleProcessor pp = new PuzzleProcessor();

    @AfterEach
    void tearDown() {
        trailheadsCoordinates.clear();
    }

    @Test
    void shouldReturn_Expected2DArray_ForPuzzleTest1_Part1() {
        String fileName = "puzzleTest1.txt";
        int dimensions = 4;
        int[][] expectedResult = {
                {0, 1, 2, 3},
                {1, 2, 3, 4},
                {8, 7, 6, 5},
                {9, 8, 7, 6}};
        int[][] actualResult = pp.readFromInputFile(fileName, dimensions, dimensions);
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturn_ListSize1_Coords0And0_ForPuzzleTest1_Part1() {
        String fileName = "puzzleTest1.txt";
        int dimensions = 4;
        List<int[]> expectedResult = List.of(new int[]{0,0});
        pp.readFromInputFile(fileName, dimensions, dimensions);
        assertEquals(expectedResult.size(), trailheadsCoordinates.size());
        assertArrayEquals(expectedResult.getFirst(), trailheadsCoordinates.getFirst());
    }

    @Test
    void shouldReturn_Expected2DArray_ForPuzzleTest2_Part1() {
        String fileName = "puzzleTest2.txt";
        int dimensions = 8;
        int[][] expectedResult = {
                {8, 9, 0, 1, 0, 1, 2, 3},
                {7, 8, 1, 2, 1, 8, 7, 4},
                {8, 7, 4, 3, 0, 9, 6, 5},
                {9, 6, 5, 4, 9, 8, 7, 4},
                {4, 5, 6, 7, 8, 9, 0, 3},
                {3, 2, 0, 1, 9, 0, 1, 2},
                {0, 1, 3, 2, 9, 8, 0, 1},
                {1, 0, 4, 5, 6, 7, 3, 2}};
        int[][] actualResult = pp.readFromInputFile(fileName, dimensions, dimensions);
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturn_ListSize9_ForPuzzleTest2_Part1() {
        String fileName = "puzzleTest2.txt";
        int dimensions = 8;
        int expectedResult = 9;
        pp.readFromInputFile(fileName, dimensions, dimensions);
        assertEquals(expectedResult, trailheadsCoordinates.size());
    }

}