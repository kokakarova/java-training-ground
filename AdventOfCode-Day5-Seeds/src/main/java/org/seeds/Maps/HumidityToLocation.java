package org.seeds.Maps;

import lombok.Data;

@Data
public class HumidityToLocation {
    int[][]humidityToLocationMap;
    boolean active = false;
}
