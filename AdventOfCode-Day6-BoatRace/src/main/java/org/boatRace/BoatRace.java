package org.boatRace;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class BoatRace {

    List<Integer> timeAndWinScenarios = new ArrayList<>();
    List<Integer> recordDistances = new ArrayList<>();
    int[] winScenariosCount;

    public BoatRace(List<Integer> timeAndWinScenarios, List<Integer> recordDistances) {
        for (int i = 0; i < timeAndWinScenarios.size(); i++) {
            this.timeAndWinScenarios.add(timeAndWinScenarios.get(i));
            this.recordDistances.add(recordDistances.get(i));
        }
        this.winScenariosCount = new int[timeAndWinScenarios.size()];
    }

    public int getFinalResult() {
        for (int i = 0; i < timeAndWinScenarios.size(); i++) {
            getCountForRace(i);
        }
        return Arrays.stream(winScenariosCount).reduce(1, (a, b) -> a * b);
    }

    private void getCountForRace(int arrayIndex) {
        int totalTime = timeAndWinScenarios.get(arrayIndex);
        int distanceToBeat = recordDistances.get(arrayIndex);
        int holdTime = 1;
        int travelTime = (totalTime - holdTime) * holdTime;
        int countOptions = 0;
        while (travelTime > distanceToBeat || holdTime <= travelTime) {
            if (travelTime > distanceToBeat) {
                countOptions++;
            }
            holdTime++;
            travelTime = (totalTime - holdTime) * holdTime;
        }
        winScenariosCount[arrayIndex] = countOptions;
    }
}
