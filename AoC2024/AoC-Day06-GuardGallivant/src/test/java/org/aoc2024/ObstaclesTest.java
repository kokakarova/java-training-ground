package org.aoc2024;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObstaclesTest {
    FileReader fileReader = new FileReader();
    Obstacles obstacles = new Obstacles();
    String fileName = "puzzleTest.txt";

        @Test
        void shouldReturn6_forPuzzleTest_Part2() {
            int expectedResult = 6;
            int rows = 10;
            int cols = 10;
            StartingGrid startingGrid = fileReader.getPatrolGrid(fileName, rows, cols);
            Navigator nav = new Navigator(0, startingGrid.getStartingPosition(), startingGrid.getNextToStartingPosition());
            obstacles.countObstacles(startingGrid, nav, false);
            assertEquals(expectedResult, obstacles.getObstacleCount());
        }

}