package org.example;

import java.io.IOException;
import java.util.MissingFormatArgumentException;

public class GearRatios {
    public static void main(String[] args) throws IOException {
        CalculateMissingParts missingParts = new CalculateMissingParts();
        try {
            missingParts.readFromFile("puzzleFile.txt");
            System.out.println("missingParts.getTotalSum() = " + missingParts.getTotalSum());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}