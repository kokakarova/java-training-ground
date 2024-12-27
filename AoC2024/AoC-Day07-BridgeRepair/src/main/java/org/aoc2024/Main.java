package org.aoc2024;


import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        String fileName = "puzzle.txt";
        InputProcessor ip = new InputProcessor();
        ip.readEquationsFromInput(fileName, 1);
        System.out.println("Part 1 RESULT ---> " + Equation.validEquations);
        long endTime   = System.currentTimeMillis();
        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.print("Execution time is " + formatter.format((endTime - startTime) / 1000d) + " seconds");
    }
}