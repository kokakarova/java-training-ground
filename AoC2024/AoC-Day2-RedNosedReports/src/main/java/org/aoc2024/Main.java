package org.aoc2024;

public class Main {
    public static void main(String[] args) {
        final Solution solution = new Solution();
//        solution.getSolution("puzzle.txt", 1);
//        System.out.println("PART_1 safe levels: " + solution.getSafeReportsCount());
        solution.getSolution("puzzle.txt", 2);
        System.out.println("PART_2 safe levels: " + solution.getSafeReportsCount());
    }
}