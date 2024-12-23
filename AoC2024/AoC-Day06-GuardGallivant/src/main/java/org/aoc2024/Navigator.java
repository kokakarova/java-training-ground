package org.aoc2024;

import lombok.Data;


@Data
public class Navigator {
    private int movement = 0;
    int[] currentStep = new int[2];
    int[] nextStep = new int[2];

    public int updateMovementDirection(int movement) {
        return movement == 3 ? 0 : movement + 1;
    }

    public int[] updateNextStep(int[] currentPosition, int movement) {
        int[][] movingDirections = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        return new int[]{currentPosition[0] + movingDirections[movement][0], currentPosition[1] + movingDirections[movement][1]};
    }

    public boolean checkStepScope(int[] nextStep, int len) {
        int xCoord = nextStep[0];
        int yCoord = nextStep[1];
        return xCoord >= 0 && xCoord < len && yCoord >= 0 && yCoord < len;
    }
}
//public void countGuardSteps(StartingGrid grid, int partNumber, int movement) {
//    System.out.println("----------------------------");
//    System.out.println("partNumber = " + partNumber);
//    navigator.setMovement(movement);
//    navigator.setCurrentStep(grid.getStartingPosition());
//    navigator.setNextStep(getNextStep(navigator.getCurrentStep(), movement));
//
//    List<String> visitedPositions = new ArrayList<>();
////        int movement = 0;
//    int len = grid.getGrid().length;
//    boolean inScope = true;
//    int[] currentStepCoords = grid.getStartingPosition();
//    int[] nextStepCoords = getNextStep(currentStepCoords, movement);
////        int[] currentStepCoords22 = currentStepCoords.clone();
////        int[] nextStepCoords22 = nextStepCoords.clone();
//    nextStepFromStartingPosition = nextStepCoords.clone();
//    String visited = currentStepCoords[0] + "," + currentStepCoords[1];
//    visitedPositions.add(visited);
//    while (inScope) {
//        if (!checkStepScope(nextStepCoords, len)) {
//            inScope = false;
//        } else {
//            char charAtNextStep = grid.getGrid()[nextStepCoords[0]][nextStepCoords[1]];
//            if (charAtNextStep == '.') {
//                switch (partNumber) {
//                    case 1 -> {
//                        currentStepCoords = nextStepCoords;
//                        nextStepCoords = getNextStep(currentStepCoords, movement);
//                        if (!visitedPositions.contains(currentStepCoords[0] + "," + currentStepCoords[1])) {
//                            stepsCount++;
//                            visitedPositions.add(currentStepCoords[0] + "," + currentStepCoords[1]);
//                        }
//                    }
//                    case 2 -> {
//                        startPosition[0] = currentStepCoords[0];
//                        startPosition[1] = currentStepCoords[1];
//                        char[][] newGrid = grid.clone();
//                        newGrid[currentStepCoords[0]][currentStepCoords[1]] = '.';
//                        newGrid[nextStepCoords[0]][nextStepCoords[1]] = '#';
//                        int movement22 = updateMovementDirection(movement);
//                        countGuardSteps(newGrid, 22, movement22);
//                    }
//                    case 22 -> {
//                        if (visitedPositions.isEmpty()) {
//                            visitedPositions.add(startPosition[0] + "," + startPosition[1]);
//                            visitedPositions.add(nextStepFromStartingPosition[0] + "," + nextStepFromStartingPosition[1]);
//                        }
//                        currentStepCoords22 = nextStepCoords22;
//                        nextStepCoords22 = getNextStep(currentStepCoords22, movement);
//                        if (visitedPositions.get(0).equals(currentStepCoords22[0] + "," + currentStepCoords22[1])
//                                && visitedPositions.get(1).equals(nextStepCoords22[0] + "," + nextStepCoords22[1])) {
//                            obstaclesCount++;
//                            inScope = false;
//                        }
//                    }
//                    default ->
//                            throw new IllegalStateException("Something went wrong at\nCurrent Step: x[" + currentStepCoords[0] + "," + currentStepCoords[1] + "]\nPartNumber: " + partNumber);
//                }
//            } else {
//                movement = updateMovementDirection(movement);
//                nextStepCoords = getNextStep(currentStepCoords, movement);
//            }
//        }
//    }
//}