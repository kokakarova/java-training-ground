package org.PipeMaze;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Data
public class PipeMaze {
    Set<Float> pipeVertexes = new TreeSet<>();
    Set<Float> dashVertexes = new TreeSet<>();
    Set<Float> lSignVertexes = new TreeSet<>();
    Set<Float> jSignVertexes = new TreeSet<>();
    Set<Float> sevenSignVertexes = new TreeSet<>();
    Set<Float> fSignVertexes = new TreeSet<>();
    float startingPosition;

    public void readFromFile(String fileName, int part) {
        int lineNumber = 0;
        try (InputStream file = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                lineNumber++;
                char[] lineChars = line.toCharArray();
                allocateCharacters(lineNumber, lineChars);
            }
            System.out.println("pipeVertexes.size() = " + pipeVertexes.size());
            System.out.println("pipeVertexes = " + pipeVertexes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void allocateCharacters(int lineNumber, char[] lineChars) {
        for (int i = 0; i < lineChars.length; i++) {
            float locationValue = (float)lineNumber + (float) (i * 1.0) / 1000;
            switch (lineChars[i]) {
                case 'S' -> startingPosition = locationValue;
                case '|' -> {
                    pipeVertexes.add(locationValue);
                }
                case '-' -> dashVertexes.add(locationValue);
                case 'L' -> lSignVertexes.add(locationValue);
                case 'J' -> jSignVertexes.add(locationValue);
                case '7' -> sevenSignVertexes.add(locationValue);
                case 'F' -> fSignVertexes.add(locationValue);
                default -> {
                    continue;
                }
            }
        }
    }

}
