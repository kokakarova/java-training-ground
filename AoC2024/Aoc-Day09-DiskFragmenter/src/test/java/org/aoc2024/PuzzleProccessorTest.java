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
        assertEquals(expected, processor.readFromInputFile(fileName));
    }

    @Test
    void shouldReturn_ExpectedForPuzzleTest2_Part1() {
        PuzzleProccessor processor = new PuzzleProccessor();
//        String expected = "0..111....22222";
        List<Integer> expected = List.of(0, -1, -1, 1, 1, 1, -1, -1, -1, -1, 2, 2, 2, 2, 2);
        String fileName = "puzzleTest2.txt";
        assertEquals(expected, processor.readFromInputFile(fileName));
    }

    @Test
    void shouldReturn_ExpectedArrangedDiskList_ForPuzzleTest2_Part1() {
        PuzzleProccessor processor = new PuzzleProccessor();
//        String expected = "022111222......";
        List<Integer> expected = List.of(0,2,2,1,1,1,2,2,2,-1,-1,-1,-1,-1,-1);
        String fileName = "puzzleTest2.txt";
        List<Integer> initialProcessedDiskList = processor.readFromInputFile(fileName);
        assertEquals(expected, processor.processFileCompacting(initialProcessedDiskList));
    }

    @Test
    void shouldReturn_ExpectedArrangedDiskList_ForPuzzleTest_Part1() {
        PuzzleProccessor processor = new PuzzleProccessor();
//        String expected = "0099811188827773336446555566..............";
        List<Integer> expected = List.of(0,0,9,9,8,1,1,1,8,8,8,2,7,7,7,3,3,3,6,4,4,6,5,5,5,5,6,6,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1);
        String fileName = "puzzleTest.txt";
        List<Integer> initialProcessedDiskList = processor.readFromInputFile(fileName);
        assertEquals(expected, processor.processFileCompacting(initialProcessedDiskList));
    }

    @Test
    void shouldReturn1928_ForPuzzleTest_Part1() {
        PuzzleProccessor processor = new PuzzleProccessor();
        int expected = 1928;
        String fileName = "puzzleTest.txt";
        List<Integer> initialProcessedDiskList = processor.readFromInputFile(fileName);
        List<Integer> processedDiskList = processor.processFileCompacting(initialProcessedDiskList);
        assertEquals(expected, processor.getFilesystemCheckSum(processedDiskList));
    }

}