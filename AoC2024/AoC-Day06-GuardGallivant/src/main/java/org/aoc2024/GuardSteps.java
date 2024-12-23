package org.aoc2024;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GuardSteps {

    Navigator nav = new Navigator();
    int stepsCount = 1;

    public void countGuardSteps(StartingGrid grid, int movement) {
        nav.setMovement(movement);
        nav.setCurrentStep(grid.getStartingPosition());
        nav.setNextStep(grid.getNextToStartingPosition());

        List<String> visitedPositions = new ArrayList<>();
        int len = grid.getGrid().length;
        boolean inScope = true;
        String visited = nav.getCurrentStep()[0] + "," + nav.getCurrentStep()[1];
        visitedPositions.add(visited);
        while (inScope) {
            if (!nav.checkStepScope(nav.getNextStep(), len)) {
                inScope = false;
            } else {
                char charAtNextStep = grid.getGrid()[nav.getNextStep()[0]][nav.getNextStep()[1]];
                if (charAtNextStep == '.') {
                    nav.setCurrentStep(nav.getNextStep());
                    nav.setNextStep(nav.updateNextStep(nav.getCurrentStep(), movement));
                    if (!visitedPositions.contains(nav.getCurrentStep()[0] + "," + nav.getCurrentStep()[1])) {
                        stepsCount++;
                        visitedPositions.add(nav.getCurrentStep()[0] + "," + nav.getCurrentStep()[1]);
                    }
                } else {
                    movement = nav.updateMovementDirection(movement);
                    nav.setNextStep(nav.updateNextStep(nav.getCurrentStep(), movement));
                }
            }
        }
    }

//    private boolean loopCompleted(int[] currentStepCoords, int[] nextStepCoords) {
//        return Arrays.equals(currentStepCoords, startPosition) && Arrays.equals(nextStepCoords, nextStepFromStartingPosition);
//    }

//    private int getMovementForSubGrid(int[] currentStepCoords, int[] nextStepCoords) {
//        if (currentStepCoords[0] == nextStepCoords[0]) {
//            return currentStepCoords[1] > nextStepCoords[1] ? 0 : 2;
//        }
//        return currentStepCoords[0] > nextStepCoords[0] ? 1 : 3;
//
//    }
}


