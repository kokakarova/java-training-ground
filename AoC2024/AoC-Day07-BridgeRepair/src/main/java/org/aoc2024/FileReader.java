package org.aoc2024;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Data
public class FileReader {

    public void readEquationsFromInput(String fileName) {
        try (InputStream file = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            int rowNum = 0;
            Equation equation = new Equation();
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                equation = splitStringToEquation(line);
                equation.checkEquation(equation);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    private Equation splitStringToEquation(String line) {
        Equation equation = new Equation();
        String[] numsStrings = line.split(":");
        equation.setResult(Integer.parseInt(numsStrings[0]));
        String[] valuesStrings = numsStrings[1].trim().split(" ");
        List<Integer> values = new ArrayList<>();
        for (String n : valuesStrings) {
            values.add(Integer.parseInt(n));
        }
        equation.setValues(values);
        return equation;
    }
}
