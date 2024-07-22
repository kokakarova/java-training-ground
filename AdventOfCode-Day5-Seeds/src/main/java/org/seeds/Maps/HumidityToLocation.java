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
            }
        }
    }

    public long getLocationPlacement(long humidityPlace) {
        for (var line : humidityToLocationMap) {
            if (humidityPlace >= line[1] && humidityPlace < line[1] + line[2]) {
                return line[0] + (humidityPlace - line[1]);
            }
        }
        return humidityPlace;
    }
}
