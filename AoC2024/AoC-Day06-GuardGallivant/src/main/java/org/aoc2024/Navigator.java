package org.aoc2024;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Navigator {
    private int movement;
    private int[] currentStep = new int[2];
    private int[] nextStep = new int[2];

    public int updateMovementDirection(int movement) {
        return movement == 3 ? 0 : movement + 1;
    }

    public int[] updateNextStep(int[] currentPosition, int movement) {
        int[][] movingDirections = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        return new int[]{currentPosition[0] + movingDirections[movement][0], currentPosition[1] + movingDirections[movement][1]};
    }

    public boolean outOfScope(int[] nextStep, int len) {
        int xCoord = nextStep[0];
        int yCoord = nextStep[1];
        return xCoord < 0 || xCoord >= len || yCoord < 0 || yCoord >= len;
    }
}
