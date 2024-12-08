package org.example;

import java.io.IOException;

public class GearRatios {
    public static void main(String[] args) throws IOException {
        CalculateMissingParts missingParts = new CalculateMissingParts();
        BrokenGears brokenGears = new BrokenGears();
        try {
            missingParts.readFromFile("puzzleFile.txt");
            System.out.println("Part-1, final sum: " + missingParts.getTotalSum());
            brokenGears.readFromFile("puzzleFile.txt");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}