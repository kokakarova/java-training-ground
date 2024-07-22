package org.seeds.Maps;

import lombok.Data;

import java.util.List;

@Data
public class SoilToFertilizer {
    long[][] soilToFertilizerMap;
    boolean active = false;

    public void setSoilToFertilizerMap(List<String[]> soilToFertilizerMapList) {
        soilToFertilizerMap = new long[soilToFertilizerMapList.size()][3];
        for (int i = 0; i < soilToFertilizerMapList.size(); i++) {
            for (int j = 0; j < soilToFertilizerMapList.get(i).length; j++) {
                soilToFertilizerMap[i][j] = Long.parseLong(soilToFertilizerMapList.get(i)[j]);
            }
        }
    }

    public long getFertilizerPlacement(long soilPlace) {
        for (var line : soilToFertilizerMap) {
            if (soilPlace >= line[1] && soilPlace < line[1] + line[2]) {
                return line[0] + (soilPlace - line[1]);
            }
        }
        return soilPlace;
    }
}
