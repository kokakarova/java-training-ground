package org.example;

import java.sql.Time;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PuzzleProcessor pp = new PuzzleProcessor();
        System.out.println("How many times do you plan to blink?");
        System.out.print("---> ");
        Scanner in = new Scanner(System.in);
        int blinks = in.nextInt();
        long startTime = System.currentTimeMillis();
        String fileName = "puzzleInput.txt";
        pp.readFromInputFile(fileName);
        System.out.println("Processing for solution started ...");
        System.out.println("Start Time - - - - - - " + LocalTime.now());
        // using non-recursive
//        pp.blinkAtTheStonesPart1(blinks);
//        int totalStones = PuzzleProcessor.stonesList.size();
        // using recursive
        long totalStones = pp.getFinalTotalStones(blinks);
        long endTime   = System.currentTimeMillis();
        System.out.println("After " + blinks + " blinks there will be " + totalStones + " stones");
        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.println("- - - - - - - - - ");
        System.out.print("Execution time is " + formatter.format((endTime - startTime) / 1000d) + " seconds");
    }
}