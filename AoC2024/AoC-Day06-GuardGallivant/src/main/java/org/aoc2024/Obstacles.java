package org.aoc2024;

import lombok.Data;

@Data
public class Obstacles {
    private int obstacleCount = 0;

    public void countObstacles(StartingGrid startingGrid, Navigator nav, boolean isSubGrid) {
        int len = startingGrid.getGrid().length;
        boolean inScope = true;
        while (inScope) {

            if (!nav.checkStepScope(nav.getNextStep(), len)) {
                inScope = false;
            } else {
                char charAtNextStep = startingGrid.getGrid()[nav.getNextStep()[0]][nav.getNextStep()[1]];
                if (!isSubGrid) {
//                    startPosition[0] = currentStepCoords[0];
//                    startPosition[1] = currentStepCoords[1];
                    char[][] newGrid = startingGrid.getGrid().clone();
                    newGrid[nav.getCurrentStep()[0]][nav.getCurrentStep()[1]] = '.';
                    newGrid[nav.getNextStep()[0]][nav.getNextStep()[1]] = '#';
                    int newMovement = nav.updateMovementDirection(nav.getMovement());
                    StartingGrid subGrid = new StartingGrid(newGrid, nav.getCurrentStep(), nav.updateNextStep(nav.getCurrentStep(), newMovement));
//                    countGuardSteps(subGrid, 22, newMovement);

                }
            }
        }
    }
}
