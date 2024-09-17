package org.seeds.Maps;

import lombok.Data;

import java.util.List;

@Data
public class TemperatureToHumidity {
    long[][] temperatureToHumidityMap;
    boolean active = false;

    public void setTempToHumidityMap(List<String[]> tempToHumidityMapList) {
        temperatureToHumidityMap = new long[tempToHumidityMapList.size()][3];
        for (int i = 0; i < tempToHumidityMapList.size(); i++) {
            for (int j = 0; j < tempToHumidityMapList.get(i).length; j++) {
                temperatureToHumidityMap[i][j] = Long.parseLong(tempToHumidityMapList.get(i)[j]);
            }
        }
    }

    public long getHumidityPlacement(long temperaturePlace) {
        for (var line : temperatureToHumidityMap) {
            if (temperaturePlace >= line[1] && temperaturePlace < line[1] + line[2]) {
                return line[0] + (temperaturePlace - line[1]);
            }
        }
        return temperaturePlace;
    }
}
