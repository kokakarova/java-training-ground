package org.aoc2024;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PuzzleProccessor proccessor = new PuzzleProccessor();
        String fileName = "puzzle.txt";
        List<Integer> processedDiskList = proccessor.readFromInputFile(fileName);
        List<Integer> formatedDiskList = proccessor.processFileCompacting(processedDiskList);
        System.out.println("Part 1 result ---> " + proccessor.getFilesystemCheckSum(formatedDiskList));
    }
}