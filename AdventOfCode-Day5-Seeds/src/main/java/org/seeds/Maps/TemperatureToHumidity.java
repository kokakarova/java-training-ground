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
                System.out.println("temperatureToHumidityMap[" + i + "][" + j +"] = " + temperatureToHumidityMap[i][j]);
            }
        }
    }
}
