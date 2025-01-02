package org.aoc2024;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Data
public class PuzzleProcessor {

    public static Map<Character, List<int[]>> frequenciesAntennas = new HashMap<>();
    private int range; // TEST: 11, FULL: 49

    public PuzzleProcessor(int range) {
        this.range = range;
    }

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

    // called from main/test
    public Set<String> findAntinodesForAllAntennas() {
        // nested for loops
        // for each char (frequency)
        Set<String> antinodes = new HashSet<>();
        for (Map.Entry<Character, List<int[]>> entry : frequenciesAntennas.entrySet()) {
            // for each list of coordinates find antinodes for the pairs
            // and add all to antinodes var
            antinodes.addAll(findAntinodesForOneAntenna(entry.getValue()));
        }
        return antinodes;
    }

    private Set<String> findAntinodesForOneAntenna(List<int[]> antennas) {
        Set<String> antinodes = new HashSet<>();
        int len = antennas.size();
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                AntennaPair pair = new AntennaPair(antennas.get(i), antennas.get(j));
                antinodes.addAll(calculateAntinodes(pair));
            }
        }
        return antinodes;
    }

    private List<String> calculateAntinodes(AntennaPair pair) {
        List<String> antinodesString = new ArrayList<>();
        int antinode1X = pair.getAntenna1()[0] - pair.getRelationCoords()[0];
        int antinode1Y = pair.getAntenna1()[1] - pair.getRelationCoords()[1];
        if (isInRange(antinode1X) && isInRange(antinode1Y)) {
            antinodesString.add(antinode1X + ", " + antinode1Y);
        }
        int antinode2X = pair.getAntenna2()[0] + pair.getRelationCoords()[0];
        int antinode2Y = pair.getAntenna2()[1] + pair.getRelationCoords()[1];
        if (isInRange(antinode2X) && isInRange(antinode2Y)) {
            antinodesString.add(antinode2X + ", " + antinode2Y);
        }
        return antinodesString;
    }

    private boolean isInRange(int coordinate) {
        return coordinate >= 0 && coordinate <= range;
    }
}
