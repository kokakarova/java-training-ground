package org.seeds.Maps;

import lombok.Data;

import java.util.List;

@Data
public class SeedToSoil {
    long[][] seedToSoilMap;
    boolean active = false;

    public void setSeedToSoilMap(List<String[]> seedToSoilMapList) {
        seedToSoilMap = new long[seedToSoilMapList.size()][3];
        for (int i = 0; i < seedToSoilMapList.size(); i++) {
            for (int j = 0; j < seedToSoilMapList.get(i).length; j++) {
                seedToSoilMap[i][j] = Long.parseLong(seedToSoilMapList.get(i)[j]);
                System.out.println("seedToSoilMap[" + i + "][" + j +"] = " + seedToSoilMap[i][j]);
            }
        }
    }
}
