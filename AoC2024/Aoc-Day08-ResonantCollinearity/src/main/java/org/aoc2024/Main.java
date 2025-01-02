package org.aoc2024;

public class Main {
    public static void main(String[] args) {
        PuzzleProcessor pp = new PuzzleProcessor(49);
        String fileName = "puzzle.txt";
        pp.readAntennasFromInput(fileName);
        System.out.println("Part 1 result ---> " + pp.findAntinodesForAllAntennas().size());
    }
}