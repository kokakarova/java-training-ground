package org.seeds.Maps;

import lombok.Data;

import java.util.List;

@Data
public class WaterToLight {
    long[][] waterToLightMap;
    boolean active = false;

    public void setWaterToLightMap(List<String[]> waterToLightMapList) {
        waterToLightMap = new long[waterToLightMapList.size()][3];
        for (int i = 0; i < waterToLightMapList.size(); i++) {
            for (int j = 0; j < waterToLightMapList.get(i).length; j++) {
                waterToLightMap[i][j] = Long.parseLong(waterToLightMapList.get(i)[j]);
            }
        }
    }

    public long getLightPlacement(long waterPlace) {
        for (var line : waterToLightMap) {
            if (waterPlace >= line[1] && waterPlace < line[1] + line[2]) {
                return line[0] + (waterPlace - line[1]);
            }
        }
        return waterPlace;
    }
}
