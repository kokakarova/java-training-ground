package org.aoc2024;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Data
public class PuzzleProcessor {

    Map<Character, List<int[]>> frequenciesAntennas = new HashMap<>();

    public void readAntennasFromInput(String fileName) {
        try (InputStream file = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            int l = 0;
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (c != '.') {
                        addAntennaToMap(c, l, i);
//                        antennas.put(line.charAt(i), antennas.getOrDefault(c, 1) + 1);
                    }
                }
                l++;
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    private void addAntennaToMap(char c, int row, int col) {
        int[] coords = new int[]{row, col};
        if (frequenciesAntennas.containsKey(c)) {
            frequenciesAntennas.get(c).add(coords);
            return;
        }
        List<int[]> listOfCoords = new ArrayList<>(List.of(coords));
        frequenciesAntennas.put(c, listOfCoords);
    }
}
