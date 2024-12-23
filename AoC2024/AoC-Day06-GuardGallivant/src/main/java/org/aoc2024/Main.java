package org.aoc2024;


public class Main {
    public static void main(String[] args) {
        GuardSteps guardSteps = new GuardSteps();
        FileReader fileReader = new FileReader();
        int rows = 130;
        int cols = 130;
        String fileName = "puzzle.txt";
        StartingGrid grid = fileReader.getPatrolGrid(fileName, rows, cols);
        guardSteps.countGuardSteps(grid, 0);
        System.out.println("PART_1 solution ----> " + guardSteps.stepsCount);
//        solutionPart1.getSolution(grid, 2, 0);
//        System.out.println("PART_2 solution ----> " + solutionPart1.stepsCount);
    }
}