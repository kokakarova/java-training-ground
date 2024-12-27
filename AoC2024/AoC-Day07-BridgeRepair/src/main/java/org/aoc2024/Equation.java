package org.aoc2024;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Equation {
    private long eqResult;
    private long[] eqNumbers;
    public static char[] operators = {'+', '*'};
    public static long validEquations = 0;

    public boolean isValidEquationWithTwoOperators(Equation eq, long[] eqOperators) {
        System.out.println("in isValidEquationWithTwoOperators");
        System.out.println("Arrays.toString(eq.getEqNumbers()) = " + Arrays.toString(eq.getEqNumbers()));
        System.out.println("eqOperators = " + Arrays.toString(eqOperators));
        long localResult1 = eq.eqNumbers[0];
        for (int i = 1, j = 0; i < eq.eqNumbers.length; i++, j++) {
            if (localResult1 > eq.getEqResult()) {
                return false;
            }
            if (operators[(int) eqOperators[j]] == '+') {
                localResult1 += eq.eqNumbers[i];
            } else {
                localResult1 *= eq.eqNumbers[i];
            }
        }
        return localResult1 == eq.getEqResult();
    }

    public boolean isValidEquation(Equation eq, long[] eqOperators, int partNumber) {
        if (partNumber == 1) {
            return isValidEquationWithTwoOperators(eq, eqOperators);
        } else {
            int occurrencesOf2 = findOccurrencesOf2(eqOperators);
            if (occurrencesOf2 == eqOperators.length) {
                return concatenateNumbers(eq.getEqNumbers()) == eq.getEqResult();
            }
            if (occurrencesOf2 == 0) {
                return isValidEquationWithTwoOperators(eq, eqOperators);
            }
            long[] newEqOperatorsArr = new long[eqOperators.length - occurrencesOf2];
            long[] newNumsArr = eq.getEqNumbers();
            int indexOf2 = ArrayUtils.indexOf(eqOperators, 2);
            while (indexOf2 != -1) {
                // edit arr of nums and arr or operators
                long[] newOpsArr = ArrayUtils.remove(eqOperators, indexOf2);
                newNumsArr = getNewNumbersArray(newNumsArr, indexOf2);
                indexOf2 = ArrayUtils.indexOf(newEqOperatorsArr, 2);
                if (indexOf2 != -1) {
                    newEqOperatorsArr = newOpsArr;
                    return isValidEquationWithTwoOperators(new Equation(eq.getEqResult(), newNumsArr), newEqOperatorsArr);
                }
            }
            return isValidEquationWithTwoOperators(eq, eqOperators);
        }
    }

    // WIP
    private long[] getNewNumbersArray(long[] eqNumbers, int indexOf2) {
        System.out.println(" === in getNewNumbersArray === ");
        System.out.println("eqNumbers = " + Arrays.toString(eqNumbers));
        long[] newNumsArr = new long[eqNumbers.length - 1];
        for (int i = 0, j = 0; j < eqNumbers.length; i++, j++) {
            if (j == indexOf2) {
                newNumsArr[i] = concatenateNumbers(new long[]{eqNumbers[j], eqNumbers[j + 1]});
                j++;
                continue;
            }
            newNumsArr[i] = eqNumbers[j];
        }
        System.out.println("newNumsArr = " + Arrays.toString(newNumsArr));

        return newNumsArr;
    }

    private long concatenateNumbers(long[] eqNumbers) {
        StringBuilder concatNums = new StringBuilder();
        for (long i : eqNumbers) {
            concatNums.append(i);
        }
        return Long.parseLong(concatNums.toString());
    }

    private int findOccurrencesOf2(long[] eqOperators) {
        int occurrences = 0;
        for (long n : eqOperators) {
            if (n == 2) {
                occurrences++;
            }
        }
        return occurrences;
    }

    public long[] removeElementFromArr(long[] arr, int index) {
        return ArrayUtils.remove(arr, index);
    }
}
