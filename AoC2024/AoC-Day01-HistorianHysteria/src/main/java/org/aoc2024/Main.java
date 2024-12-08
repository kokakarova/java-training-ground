package org.aoc2024;

public class Main {
    public static void main(String[] args) {
        final Solution solution = new Solution();
        String fileName = "puzzle.txt";
        System.out.println("Solution: " + solution.readFromFile(fileName));
    }
}