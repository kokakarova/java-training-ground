package org.aoc2024;

import lombok.Data;

@Data
public class Equation {
    private long eqResult;
    private int[] eqNumbers;
    public static char[] operators = {'+', '*'};
    public static long validEquations = 0;

    public boolean isValidEquation(Equation eq, int[] eqOperators) {
        long localResult1 = eq.eqNumbers[0];
        for (int i = 1, j= 0; i < eq.eqNumbers.length; i++, j++) {
            if (operators[eqOperators[j]] == '+') {
                localResult1 += eq.eqNumbers[i];
            } else {
                localResult1 *= eq.eqNumbers[i];
            }
        }
        return localResult1 == eq.getEqResult();
    }
}
