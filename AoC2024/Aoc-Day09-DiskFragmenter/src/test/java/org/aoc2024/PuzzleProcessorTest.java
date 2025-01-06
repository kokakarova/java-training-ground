package org.aoc2024;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleProcessorTest {

    @Test
    void shouldReturn_111String_ForInput_int3_Char1() {
        PuzzleProcessor processor = new PuzzleProcessor();
        List<Integer> expected = List.of(1, 1, 1);
        assertEquals(expected, processor.addFileToString(1, 3));
    }

    @Test
    void shouldReturn_ExpectedForPuzzleTest_Part1() {
        PuzzleProcessor processor = new PuzzleProcessor();
        List<Integer> expected = List.of(0, 0, -1, -1, -1, 1, 1, 1, -1, -1, -1, 2, -1, -1, -1, 3, 3, 3, -1, 4, 4, -1, 5, 5, 5, 5, -1, 6, 6, 6, 6, -1, 7, 7, 7, -1, 8, 8, 8, 8, 9, 9);
        String fileName = "puzzleTest.txt";
        assertEquals(expected, processor.readFromInputFile(fileName));
    }

    @Test
    void shouldReturn_ExpectedForPuzzleTest2_Part1() {
        PuzzleProcessor processor = new PuzzleProcessor();
        List<Integer> expected = List.of(0, -1, -1, 1, 1, 1, -1, -1, -1, -1, 2, 2, 2, 2, 2);
        String fileName = "puzzleTest2.txt";
        assertEquals(expected, processor.readFromInputFile(fileName));
    }

    @Test
    void shouldReturn_ExpectedArrangedDiskList_ForPuzzleTest2_Part1() {
        PuzzleProcessor processor = new PuzzleProcessor();
        List<Integer> expected = List.of(0, 2, 2, 1, 1, 1, 2, 2, 2, -1, -1, -1, -1, -1, -1);
        String fileName = "puzzleTest2.txt";
        List<Integer> initialProcessedDiskList = processor.readFromInputFile(fileName);
        assertEquals(expected, processor.processFileCompactingPart1(initialProcessedDiskList));
    }

    @Test
    void shouldReturn_ExpectedArrangedDiskList_ForPuzzleTest_Part1() {
        PuzzleProcessor processor = new PuzzleProcessor();
        List<Integer> expected = List.of(0, 0, 9, 9, 8, 1, 1, 1, 8, 8, 8, 2, 7, 7, 7, 3, 3, 3, 6, 4, 4, 6, 5, 5, 5, 5, 6, 6, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        String fileName = "puzzleTest.txt";
        List<Integer> initialProcessedDiskList = processor.readFromInputFile(fileName);
        assertEquals(expected, processor.processFileCompactingPart1(initialProcessedDiskList));
    }

    @Test
    void shouldReturn1928_ForPuzzleTest_Part1() {
        PuzzleProcessor processor = new PuzzleProcessor();
        int expected = 1928;
        String fileName = "puzzleTest.txt";
        List<Integer> initialProcessedDiskList = processor.readFromInputFile(fileName);
        List<Integer> processedDiskList = processor.processFileCompactingPart1(initialProcessedDiskList);
        assertEquals(expected, processor.getFilesystemCheckSum(processedDiskList));
    }

    @Test
    void shouldReturn_ListOf3_ForInputList_01110() {
        PuzzleProcessor processor = new PuzzleProcessor();
        int fileId = 1;
        List<Integer> distTest = List.of(0, 1, 1, 1, 0);
        List<Integer> expectedList = List.of(1, 2, 3);
        List<Integer> actualResult = processor.getIndexesForFileId(distTest, fileId);
        assertEquals(expectedList.size(), actualResult.size());
        assertEquals(expectedList.get(0), actualResult.get(0));
        assertEquals(expectedList.get(1), actualResult.get(1));
        assertEquals(expectedList.get(2), actualResult.get(2));
    }

    @Test
    void shouldReturn_ListOf3_ForInputList_0neg1neg1neg10() {
        PuzzleProcessor processor = new PuzzleProcessor();
        List<Integer> distTest = List.of(0, -1, -1, -1, 1);
        List<Integer> expectedList = List.of(1, 2, 3);
        List<Integer> actualResult = processor.getAvailableFreeSpaceChunk(distTest, 3, 4);
        assertEquals(expectedList.size(), actualResult.size());
        assertEquals(expectedList.get(0), actualResult.get(0));
        assertEquals(expectedList.get(1), actualResult.get(1));
        assertEquals(expectedList.get(2), actualResult.get(2));
    }

    @Test
    void shouldReturn_ListOf0_ForInputList_01110() {
        PuzzleProcessor processor = new PuzzleProcessor();
        List<Integer> distTest = List.of(0, 1, 1, 1, 0);
        List<Integer> actualResult = processor.getAvailableFreeSpaceChunk(distTest, 3, 4);
        assertEquals(0, actualResult.size());
    }

    @Test
    void shouldReturn_ListOf2_ForInputList_neg1neg1neg110_AndSize2() {
        PuzzleProcessor processor = new PuzzleProcessor();
        List<Integer> distTest = List.of(-1, -1, -1, 0, 1);
        List<Integer> actualResult = processor.getAvailableFreeSpaceChunk(distTest, 2, 4);
        assertEquals(2, actualResult.size());
    }

    @Test
    void shouldReturn_ListOf2_ForInputList_0neg1neg1neg110_AndSize2() {
        PuzzleProcessor processor = new PuzzleProcessor();
        List<Integer> distTest = List.of(0, -1, -1, -1, 1, 1);
        List<Integer> actualResult = processor.getAvailableFreeSpaceChunk(distTest, 2, 5);
        assertEquals(2, actualResult.size());
    }

    @Test
    void shouldReturn_ListOf2_ForInputList_0neg11neg1neg12_AndSize2() {
        PuzzleProcessor processor = new PuzzleProcessor();
        List<Integer> distTest = List.of(0, -1, 1, -1, -1, 2);
        int firstFileIdIndex = 5;
        List<Integer> actualResult = processor.getAvailableFreeSpaceChunk(distTest, 2, firstFileIdIndex);
        assertEquals(2, actualResult.size());
    }

    @Test
    void shouldReturn_ListOf0_ForInputList_0neg112neg1neg1_AndSize2() {
        PuzzleProcessor processor = new PuzzleProcessor();
        List<Integer> distTest = List.of(0, -1, 1, 1, -1, -1);
        int firstFileIdIndex = 2;
        List<Integer> actualResult = processor.getAvailableFreeSpaceChunk(distTest, 2, firstFileIdIndex);
        assertEquals(0, actualResult.size());
    }

    // processFileCompactingPart2
    @Test
    void shouldReturn_ExpectedList_ForPuzzleTest_Part2() {
        PuzzleProcessor processor = new PuzzleProcessor();
        String fileName = "puzzleTest.txt";
        List<Integer> expected = List.of(0, 0, 9, 9, 2, 1, 1, 1, 7, 7, 7, -1, 4, 4, -1, 3, 3, 3, -1, -1, -1, -1, 5, 5, 5, 5, -1, 6, 6, 6, 6, -1, -1, -1, -1, -1, 8, 8, 8, 8, -1, -1);
        List<Integer> initialProcessedDiskList = processor.readFromInputFile(fileName);
        List<Integer> processedDiskList = processor.processFileCompactingPart2(initialProcessedDiskList);
        assertEquals(expected.size(), processedDiskList.size());
        assertEquals(expected, processedDiskList);
    }

    @Test
    void shouldReturn2858_ForPuzzleTest_Part2() {
        PuzzleProcessor processor = new PuzzleProcessor();
        String fileName = "puzzleTest.txt";
        int expected = 2858;
        List<Integer> initialProcessedDiskList = processor.readFromInputFile(fileName);
        List<Integer> processedDiskList = processor.processFileCompactingPart2(initialProcessedDiskList);
        assertEquals(expected, processor.getFilesystemCheckSum(processedDiskList));
    }

    // replaceSpaceWithFile
    @Test
    void shouldTest_replaceSpaceWithFile() {
        PuzzleProcessor processor = new PuzzleProcessor();
        List<Integer> diskTest = new ArrayList<>(List.of(0, -1, -1, -1, 0));
        int rightMostFieldTest = 10;
        List<Integer> freeSpaceChunksTest = List.of(1, 2, 3);
        List<Integer> expectedList = List.of(0, 10, 10, 10, 0);
        var actualResult = processor.replaceSpaceWithFile(diskTest, rightMostFieldTest, freeSpaceChunksTest);
        assertEquals(expectedList.size(), actualResult.size());
    }

    // replaceSpaceWithFile
    @Test
    void shouldTest_replaceFileIndexesWithSpace() {
        PuzzleProcessor processor = new PuzzleProcessor();
        List<Integer> diskTest = new ArrayList<>(List.of(0, 1, 1, 1, 0));
        List<Integer> freeSpaceChunksTest = List.of(1, 2, 3);
        List<Integer> expectedList = List.of(0, -1, -1, -1, 0);
        var actualResult = processor.replaceFileIndexesWithSpace(diskTest, freeSpaceChunksTest);
        assertEquals(expectedList.size(), actualResult.size());
        assertEquals(expectedList, actualResult);
    }

}