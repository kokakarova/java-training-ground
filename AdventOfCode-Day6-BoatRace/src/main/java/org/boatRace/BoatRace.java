package org.boatRace;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class BoatRace {

    List<Long> timeAndWinScenarios = new ArrayList<>();
    List<Long> recordDistances = new ArrayList<>();
    long[] winScenariosCount;

    public BoatRace(List<Long> timeAndWinScenarios, List<Long> recordDistances) {
        for (int i = 0; i < timeAndWinScenarios.size(); i++) {
            this.timeAndWinScenarios.add(timeAndWinScenarios.get(i));
            this.recordDistances.add(recordDistances.get(i));
        }
        this.winScenariosCount = new long[timeAndWinScenarios.size()];
    }

    public long getFinalResult() {
        for (int i = 0; i < timeAndWinScenarios.size(); i++) {
            getCountForRace(i);
        }
        return Arrays.stream(winScenariosCount).reduce(1, (a, b) -> a * b);
    }

    private void getCountForRace(int arrayIndex) {
        long totalTime = timeAndWinScenarios.get(arrayIndex);
        long distanceToBeat = recordDistances.get(arrayIndex);
        int holdTime = 1;
        long travelTime = (totalTime - holdTime) * holdTime;
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
