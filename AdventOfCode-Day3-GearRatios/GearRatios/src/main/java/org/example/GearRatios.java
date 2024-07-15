package org.example;

import java.io.IOException;

public class GearRatios {
    public static void main(String[] args) throws IOException {
        CalculateMissingParts missingParts = new CalculateMissingParts();
        BrokenGears brokenGears = new BrokenGears();
        try {
//            missingParts.readFromFile("puzzleFile.txt");
            brokenGears.readFromFile("puzzlePart2Simple.txt");
//            System.out.println("missingParts.getTotalSum() = " + missingParts.getTotalSum());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}