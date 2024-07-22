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
                System.out.println("waterToLightMap[" + i + "][" + j +"] = " + waterToLightMap[i][j]);
            }
        }
    }
}
