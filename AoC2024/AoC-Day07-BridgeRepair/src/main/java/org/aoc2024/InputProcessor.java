package org.aoc2024;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

@Data
public class InputProcessor {


    public void readEquationsFromInput(String fileName, int partNumber) {
        try (InputStream file = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            Equation equation = new Equation();
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                System.out.println("========================");
                System.out.println("line = " + line);
                processEquationLine(line, partNumber);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    private void processEquationLine(String line, int partNumber) {
        Equation equation = splitInputString(line);
        int operatorPlaces = equation.getEqNumbers().length - 1;
        long[] eqOperators = new long[operatorPlaces];
        if (checkForAllOperatorsPermutationsRecursive(0, eqOperators, operatorPlaces, equation, partNumber)) {
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

    private long[] getNumbersFromString(String numsString) {
        return Arrays.stream(numsString.trim().split(" ")).mapToLong(Integer::parseInt).toArray();
    }

    public boolean checkForAllOperatorsPermutationsRecursive(int i, long[] arr, int opPlaces, Equation equation, int partNumber) {
        if (i == opPlaces) {
            return equation.isValidEquation(equation, arr, partNumber);
        }
        arr[i] = 0;
        if (checkForAllOperatorsPermutationsRecursive(i + 1, arr, opPlaces, equation, partNumber)) {
            return true;
        }
        arr[i] = 1;
        boolean res = checkForAllOperatorsPermutationsRecursive(i + 1, arr, opPlaces, equation, partNumber);
        if (partNumber == 2) {
            arr[i] = 2;
            return checkForAllOperatorsPermutationsRecursive(i + 1, arr, opPlaces, equation, partNumber);
        }
        return res;
    }
}
