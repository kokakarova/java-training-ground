package org.seeds.Maps;

import lombok.Data;

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
                System.out.println("fertilizerToWaterMap[" + i + "][" + j +"] = " + fertilizerToWaterMap[i][j]);
            }
        }
    }
}
