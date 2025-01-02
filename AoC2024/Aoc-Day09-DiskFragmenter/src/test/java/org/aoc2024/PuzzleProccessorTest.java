package org.aoc2024;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleProccessorTest {

    @Test
    void shouldReturn_111String_ForInput_int3_Char1() {
        PuzzleProccessor processor = new PuzzleProccessor();
        List<Integer> expected = List.of(1, 1, 1);
        assertEquals(expected, processor.addFileToString(1, 3));
    }

    @Test
    void shouldReturn_ExpectedForPuzzleTest_Part1() {
        PuzzleProccessor processor = new PuzzleProccessor();
//        String expected = "00...111...2...333.44.5555.6666.777.888899";
        List<Integer> expected = List.of(0, 0, -1, -1, -1, 1, 1, 1, -1, -1, -1, 2, -1, -1, -1, 3, 3, 3, -1, 4, 4, -1, 5, 5, 5, 5, -1, 6, 6, 6, 6, -1, 7, 7, 7, -1, 8, 8, 8, 8, 9, 9);
        String fileName = "puzzleTest.txt";
        assertEquals(expected, processor.readAntennasFromInput(fileName));
    }

    @Test
    void shouldReturn_ExpectedForPuzzleTest2_Part1() {
        PuzzleProccessor processor = new PuzzleProccessor();
//        String expected = "0..111....22222";
        List<Integer> expected = List.of(0, -1, -1, 1, 1, 1, -1, -1, -1, -1, 2, 2, 2, 2, 2);
        String fileName = "puzzleTest2.txt";
        assertEquals(expected, processor.readAntennasFromInput(fileName));
    }

}