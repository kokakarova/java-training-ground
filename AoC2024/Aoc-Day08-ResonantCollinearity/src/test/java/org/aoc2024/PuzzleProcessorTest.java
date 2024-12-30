package org.aoc2024;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class PuzzleProcessorTest {
    
    @Test
    void countDifferentChars() {
        String fileName = "puzzle.txt";
        PuzzleProcessor pp = new PuzzleProcessor();
        pp.readAntennasFromInput(fileName);
        int i = 1;
        for (char c: pp.getFrequenciesAntennas().keySet()) {
            System.out.print(i + ". character = " + c);
            System.out.print(", coords: ");
            List<int[]> coords = pp.getFrequenciesAntennas().get(c);
            for (int[] coord: coords) {
                System.out.print(Arrays.toString(coord) + ", ");
            }
            System.out.println();
            i++;
        }
    }

}