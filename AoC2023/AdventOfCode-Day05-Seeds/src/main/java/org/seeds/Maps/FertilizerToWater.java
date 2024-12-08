package org.seeds.Maps;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class FertilizerToWater {
    long[][] fertilizerToWaterMap;
    boolean active = false;

    public void setFertilizerToWaterMap(List<String[]> fertilizerToWaterMapList) {
        fertilizerToWaterMap = new long[fertilizerToWaterMapList.size()][3];
        for (int i = 0; i < fertilizerToWaterMapList.size(); i++) {
            for (int j = 0; j < fertilizerToWaterMapList.get(i).length; j++) {
                fertilizerToWaterMap[i][j] = Long.parseLong(fertilizerToWaterMapList.get(i)[j]);
            }
        }
    }

    public long getWaterPlacement(long fertilizerPlace) {
        for (var line : fertilizerToWaterMap) {
            if (fertilizerPlace >= line[1] && fertilizerPlace < line[1] + line[2]) {
                return line[0] + (fertilizerPlace - line[1]);
            }
        }
        return fertilizerPlace;
    }
}
