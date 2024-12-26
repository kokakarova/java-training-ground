package org.aoc2024;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

@Data
public class InputProcessor {


    public void readEquationsFromInput(String fileName) {
        try (InputStream file = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            Equation equation = new Equation();
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                processEquationLine(line);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    private void processEquationLine(String line) {
        Equation equation = splitInputString(line);
        int operatorPlaces = equation.getEqNumbers().length - 1;
        int[] eqOperators = new int[operatorPlaces];
        if (generateBinaryStrings(0, eqOperators, operatorPlaces, equation)) {
            Equation.validEquations += equation.getEqResult();
        }
    }

    private Equation splitInputString(String line) {
        Equation equation = new Equation();
        String[] numsStrings = line.split(":");
        equation.setEqResult(Long.parseLong(numsStrings[0]));
        equation.setEqNumbers(getNumbersFromString(numsStrings[1]));
        return equation;
    }

    private int[] getNumbersFromString(String numsString) {
        return Arrays.stream(numsString.trim().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    public boolean generateBinaryStrings(int i, int[] arr, int opPlaces, Equation equation) {
        if (i == opPlaces) {
            return equation.isValidEquation(equation, arr);
        }
        arr[i] = 0; // 0, 0, 0
        if (generateBinaryStrings(i + 1, arr, opPlaces, equation)) {
            return true;
        }
        arr[i] = 1; // 1, 0, 0
        return generateBinaryStrings(i + 1, arr, opPlaces, equation);
    }
}
