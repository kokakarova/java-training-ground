package org.example;

import java.io.IOException;
import java.util.MissingFormatArgumentException;

public class GearRatios {
    public static void main(String[] args) throws IOException {
        CalculateMissingParts missingParts = new CalculateMissingParts();
        FileReader fileReader = new FileReader();
        try {
            fileReader.readFromFile("puzzleFile.txt", 1);
            System.out.println("missingParts.getTotalSum() = " + missingParts.getTotalSum());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}