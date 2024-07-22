package org.seeds.Maps;

import lombok.Data;

import java.util.List;

@Data
public class HumidityToLocation {
    long[][]humidityToLocationMap;
    boolean active = false;

    public void setHumidityToLocationMap(List<String[]> humidityToLocationMapList) {
        humidityToLocationMap = new long[humidityToLocationMapList.size()][3];
        for (int i = 0; i < humidityToLocationMapList.size(); i++) {
            for (int j = 0; j < humidityToLocationMapList.get(i).length; j++) {
                humidityToLocationMap[i][j] = Long.parseLong(humidityToLocationMapList.get(i)[j]);
                System.out.println("humidityToLocationMap[" + i + "][" + j +"] = " + humidityToLocationMap[i][j]);
            }
        }
    }
}
