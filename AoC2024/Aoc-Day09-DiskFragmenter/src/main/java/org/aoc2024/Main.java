package org.aoc2024;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        PuzzleProcessor proccessor = new PuzzleProcessor();
        String fileName = "puzzle.txt";
        List<Integer> processedDiskList = proccessor.readFromInputFile(fileName);
        // PART 1
        List<Integer> formatedDiskList = proccessor.processFileCompactingPart1(processedDiskList);
        System.out.println("Part 1 result ---> " + proccessor.getFilesystemCheckSum(formatedDiskList));
        // PART 2
        long endTime   = System.currentTimeMillis();
        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.print("Execution time is " + formatter.format((endTime - startTime) / 1000d) + " seconds");
    }
}