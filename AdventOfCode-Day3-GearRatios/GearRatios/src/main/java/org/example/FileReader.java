package org.example;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Data
public class FileReader {

    CalculateMissingParts missingParts = new CalculateMissingParts();

    public void readFromFile(String fileName, int part) throws IOException {
        try (InputStream file = GearRatios.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            int i = 1;
            for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                if (part == 1) {
                    missingParts.processLine(s, i);
                } else {
                    System.out.println("part 2");
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
