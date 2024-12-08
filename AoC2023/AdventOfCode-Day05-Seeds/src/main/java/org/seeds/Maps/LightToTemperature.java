package org.seeds.Maps;

import lombok.Data;

import java.util.List;

@Data
public class LightToTemperature {
    long[][] lightToTemperatureMap;
    boolean active = false;

    public void setLightToTempMap(List<String[]> lightToTempMapList) {
        lightToTemperatureMap = new long[lightToTempMapList.size()][3];
        for (int i = 0; i < lightToTempMapList.size(); i++) {
            for (int j = 0; j < lightToTempMapList.get(i).length; j++) {
                lightToTemperatureMap[i][j] = Long.parseLong(lightToTempMapList.get(i)[j]);
            }
        }
    }

    public long getTemperaturePlacement(long lightPlace) {
        for (var line : lightToTemperatureMap) {
            if (lightPlace >= line[1] && lightPlace < line[1] + line[2]) {
                return line[0] + (lightPlace - line[1]);
            }
        }
        return lightPlace;
    }
}
