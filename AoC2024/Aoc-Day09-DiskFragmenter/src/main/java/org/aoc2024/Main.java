package org.aoc2024;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        PuzzleProcessor processor = new PuzzleProcessor();
        String fileName = "puzzle.txt";
        List<Integer> processedDiskList = processor.readFromInputFile(fileName);
        // PART 1
//        List<Integer> formatedDiskListPart1 = processor.processFileCompactingPart1(processedDiskList);
//        System.out.println("Part 1 result ---> " + processor.getFilesystemCheckSum(formatedDiskListPart1));
        // PART 2
        List<Integer> formatedDiskListPart2 = processor.processFileCompactingPart2(processedDiskList);
        System.out.println("Part 2 result ---> " + processor.getFilesystemCheckSum(formatedDiskListPart2));
        // END
        long endTime   = System.currentTimeMillis();
        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.print("Execution time is " + formatter.format((endTime - startTime) / 1000d) + " seconds");
    }
}