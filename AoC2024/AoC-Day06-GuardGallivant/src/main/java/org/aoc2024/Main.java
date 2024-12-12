package org.aoc2024;


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int rows = 130;
        int cols = 130;
        String fileName = "puzzle.txt";
        char[][] grid = solution.getPatrolGrid(fileName, rows, cols);
        solution.getSolution(grid);
        System.out.println("PART_1 solution ----> " + solution.stepsCount);
    }
}