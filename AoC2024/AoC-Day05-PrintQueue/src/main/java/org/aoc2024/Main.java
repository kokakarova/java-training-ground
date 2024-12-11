package org.aoc2024;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String fileName = "puzzle.txt";
        solution.getSolution(fileName);
        System.out.println("PART_1 solution: " + solution.getSumOfMiddles());
    }
}