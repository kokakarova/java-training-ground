package org.aoc2024;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        PuzzleProcessor pp = new PuzzleProcessor(49);
        String fileName = "puzzle.txt";
        pp.readAntennasFromInput(fileName);
        // PART 1
//        System.out.println("Part 1 result ---> " + pp.findAntinodesForAllAntennas(1).size());
        // PART 2
        Set<String> antinodesString = pp.findAntinodesForAllAntennas(2);
        int unmatchedAntennasPositions = pp.addUnmatchedAntennaPositions(antinodesString);
        System.out.println("Part 2 result ---> " + (antinodesString.size() + unmatchedAntennasPositions));
    }
}

// part 2, 783 (too low)