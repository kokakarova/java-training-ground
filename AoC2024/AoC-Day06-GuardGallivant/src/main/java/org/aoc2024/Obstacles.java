package org.aoc2024;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Obstacles {
    private int obstacleCount = 0;

    public void countObstacles(StartingGrid startingGrid, Navigator nav, boolean isSubGrid) {
        System.out.println("------------------------");
        System.out.println("startingGrid.getStartingPosition() = " + Arrays.toString(startingGrid.getStartingPosition()));
        System.out.println("startingGrid.getNextToStartingPosition() = " + Arrays.toString(startingGrid.getNextToStartingPosition()));
        System.out.println("isSubGrid = " + isSubGrid);
        System.out.println("nav.getMovement() = " + nav.getMovement());
        int len = startingGrid.getGrid().length;
        boolean inScope = true;
        List<String> visitedPositions = new ArrayList<>();
        if (isSubGrid) {
            visitedPositions.add(nav.getCurrentStep()[0] + ", " + nav.getCurrentStep()[1]);
        }
        while (inScope) {
            if (nav.outOfScope(nav.getNextStep(), len)) {
                System.out.println(" OUT_OF_SCOPE ");
                inScope = false;
            } else {
                char charAtNextStep = startingGrid.getGrid()[nav.getNextStep()[0]][nav.getNextStep()[1]];
                if (charAtNextStep == '.') {
                    if (!isSubGrid) {
                        if (!visitedPositions.contains(nav.getCurrentStep()[0] + ", " + nav.getCurrentStep()[1])) {
                            char[][] newGrid = startingGrid.getGrid().clone();
                            newGrid[nav.getCurrentStep()[0]][nav.getCurrentStep()[1]] = '.';
                            newGrid[nav.getNextStep()[0]][nav.getNextStep()[1]] = '#';
                            int subMovement = nav.updateMovementDirection(nav.getMovement());
                            int[] subNextStep = nav.updateNextStep(nav.getCurrentStep(), subMovement);
                            StartingGrid subGrid = new StartingGrid(newGrid, nav.getCurrentStep(), subNextStep);
                            Navigator subNav = new Navigator(subMovement, nav.getCurrentStep(), subNextStep);
                            // recursive call with altered grid
                            countObstacles(subGrid, subNav, true);

                            visitedPositions.add(nav.getCurrentStep()[0] + ", " + nav.getCurrentStep()[1]);
                        }
                        // update currentStep and NextStep
                        nav.setCurrentStep(nav.getNextStep());
                        nav.setNextStep(nav.updateNextStep(nav.getCurrentStep(), nav.getMovement()));
                    } else {
                        // do for subGrid -> update navigator (currentStep, nextStep, movement)
                        nav.setCurrentStep(nav.getNextStep());
                        nav.setNextStep(nav.updateNextStep(nav.getCurrentStep(), nav.getMovement()));

                        visitedPositions.add(nav.getCurrentStep()[0] + ", " + nav.getCurrentStep()[1]);

                        if (samePositions(startingGrid, nav) || visitedPositions.size() > 2499) {
                            System.out.println(" # POTENTIAL OBSTACLE # ");
                            obstacleCount++;
                            inScope = false;
                        }
                    }
                } else {
                    // do for '#'
                    if (!isSubGrid) {
                        System.out.println("MAIN_GRID -- nav.getMovement() = " + nav.getMovement());
                    }
                    nav.setMovement(nav.updateMovementDirection(nav.getMovement()));
                    if (!isSubGrid) {
                        System.out.println("MAIN_GRID UPDATED -- nav.getMovement() = " + nav.getMovement());
                    }
                    nav.setNextStep(nav.updateNextStep(nav.getCurrentStep(), (nav.getMovement())));
                    if (!isSubGrid) {
                        System.out.println("MAIN_GRID -- nav.getCurrentStep() = " + Arrays.toString(nav.getCurrentStep()));
                        System.out.println("MAIN_GRID UPDATED -- nav.getNextStep() = " + Arrays.toString(nav.getNextStep()));
                    }
                }
            }
        }
    }

    public boolean samePositions(StartingGrid startingGrid, Navigator nav) {
        boolean sameCurrX = startingGrid.getStartingPosition()[0] == nav.getCurrentStep()[0];
        boolean sameCurrY = startingGrid.getStartingPosition()[1] == nav.getCurrentStep()[1];
        boolean sameNextX = nav.getNextStep()[0] == nav.getCurrentStep()[0];
        boolean sameNextY = nav.getNextStep()[1] == nav.getCurrentStep()[1];
        return sameCurrX && sameCurrY && sameNextX && sameNextY;
    }
}
