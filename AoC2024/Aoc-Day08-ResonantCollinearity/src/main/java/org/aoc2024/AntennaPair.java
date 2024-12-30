package org.aoc2024;

import lombok.Data;

@Data
public class AntennaPair {
    int[] antenna1 = new int[2];
    int[] antenna2 = new int[2];
    int[] relationCoords = new int[2];

    public AntennaPair(int[] antenna1, int[] antenna2) {
        this.antenna1 = antenna1;
        this.antenna2 = antenna2;
        relationCoords[0] = antenna2[0] - antenna1[0];
        relationCoords[1] = antenna2[1] - antenna1[1];
    }

}
