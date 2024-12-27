package org.aoc2024;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;

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
//        if (checkForAllOperatorsPermutationsRecursive(0, eqOperators, operatorPlaces, equation)) {
        if (checkForAllOperatorsNumToBin(operatorPlaces, equation)) {
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

    public boolean checkForAllOperatorsPermutationsRecursive(int i, int[] arr, int opPlaces, Equation equation) {
        if (i == opPlaces) {
            return equation.isValidEquation(equation, arr);
        }
        arr[i] = 0; // 0, 0, 0
        if (checkForAllOperatorsPermutationsRecursive(i + 1, arr, opPlaces, equation)) {
            return true;
        }
        arr[i] = 1; // 1, 0, 0
        return checkForAllOperatorsPermutationsRecursive(i + 1, arr, opPlaces, equation);
    }

    public boolean checkForAllOperatorsNumToBin(int opPlaces, Equation equation) {
        String highestBinaryString = StringUtils.leftPad("", opPlaces, "1");
        int highestNum = Integer.parseInt(highestBinaryString, 2);
        int[] eqOperators = new int[opPlaces];
        for (int i = 0; i <= highestNum; i++) {
            // convert i to binary string
            String binaryI = StringUtils.leftPad(Integer.toBinaryString(i), opPlaces, "0");
            // populate operators arrays as per binary string
            populateArrayWithBinaryChars(eqOperators, binaryI);
            // check with that binary string
            if (equation.isValidEquation(equation, eqOperators)) {
                return true;
            }
        }
        return false;
    }

    private void populateArrayWithBinaryChars(int[] eqOperators, String binaryString) {
        int len = binaryString.length();
        for (int i = 0; i < len; i++) {
            eqOperators[i] = Integer.parseInt(String.valueOf(binaryString.charAt(i)));
        }
    }

//    private int getHighestNumForBinaryConversion(int opPlaces) {
//
//    }
}
