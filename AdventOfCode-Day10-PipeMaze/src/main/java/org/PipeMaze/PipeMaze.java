package org.PipeMaze;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class PipeMaze {
    Map<Integer, List<Integer>> pipeSigns = new HashMap<>();
    Map<Integer, List<Integer>> dashSigns = new HashMap<>();
    Map<Integer, List<Integer>> lSigns = new HashMap<>();
    Map<Integer, List<Integer>> jSigns = new HashMap<>();
    Map<Integer, List<Integer>> sevenSigns = new HashMap<>();
    Map<Integer, List<Integer>> fSigns = new HashMap<>();
    Map<Integer, Integer> startingPosition = new HashMap<>();

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void allocateCharacters(int lineNumber, char[] lineChars) {
        for (int i = 0; i < lineChars.length; i++) {
            switch (lineChars[i]) {
                case 'S' -> startingPosition.put(lineNumber, i);
                case '|' -> addToSignsMap("pipe", lineNumber, i);
                case '-' -> addToSignsMap("dash", lineNumber, i);
                case 'L' -> addToSignsMap("L", lineNumber, i);
                case 'J' -> addToSignsMap("J", lineNumber, i);
                case '7' -> addToSignsMap("seven", lineNumber, i);
                case 'F' -> addToSignsMap("F", lineNumber, i);
                default -> {
                    continue;
                }
            }
        }
    }

    private void addToSignsMap(String signType, int lineNumber, int i) {
        switch (signType) {
            case "pipe" -> {
                pipeSigns.putIfAbsent(lineNumber, new ArrayList<>());
                pipeSigns.get(lineNumber).add(i);
            }
            case "dash" -> {
                dashSigns.putIfAbsent(lineNumber, new ArrayList<>());
                dashSigns.get(lineNumber).add(i);
            }
            case "L" -> {
                lSigns.putIfAbsent(lineNumber, new ArrayList<>());
                lSigns.get(lineNumber).add(i);
            }
            case "J" -> {
                jSigns.putIfAbsent(lineNumber, new ArrayList<>());
                jSigns.get(lineNumber).add(i);
            }
            case "seven" -> {
                sevenSigns.putIfAbsent(lineNumber, new ArrayList<>());
                sevenSigns.get(lineNumber).add(i);
            }
            case "F" -> {
                fSigns.putIfAbsent(lineNumber, new ArrayList<>());
                fSigns.get(lineNumber).add(i);
            }
            default -> {}
        }
    }

}
