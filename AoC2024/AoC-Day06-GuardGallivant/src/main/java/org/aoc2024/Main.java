package org.aoc2024;


public class Main {
    public static void main(String[] args) {
        GuardSteps guardSteps = new GuardSteps();
        FileReader fileReader = new FileReader();
        Obstacles obstacles = new Obstacles();
        int rows = 130;
        int cols = 130;
        String fileName = "puzzle.txt";
        StartingGrid grid = fileReader.getPatrolGrid(fileName, rows, cols);
//        guardSteps.countGuardSteps(grid, 0);
//        System.out.println("PART_1 solution ----> " + guardSteps.stepsCount);

        Navigator nav = new Navigator(0, grid.getStartingPosition(), grid.getNextToStartingPosition());
        obstacles.countObstacles(grid,nav, false);
        System.out.println("PART_2 solution ----> " + obstacles.getObstacleCount());
    }
}