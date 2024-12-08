package org.aoc2024;

public class Main {
    public static void main(String[] args) {
        final Solution solution = new Solution();
        String fileName = "puzzle.txt";
        System.out.println("Solution for Part 1: " + solution.getSolution(fileName, 1));
        System.out.println("Solution for Part 2: " + solution.getSolution(fileName, 2));
    }
}