package org.aoc2024;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Equation {
    private long eqResult;
    private long[] eqNumbers;
    public static char[] operators = {'+', '*', '|'};
    public static long validEquations = 0;

    public boolean isValidEquation(Equation eq, long[] eqOperators) {
        long localResult1 = eq.eqNumbers[0];
        for (int i = 1, j = 0; i < eq.eqNumbers.length; i++, j++) {
            if (localResult1 > eq.getEqResult()) {
                return false;
            }
            switch (operators[(int) eqOperators[j]]) {
                case '+' -> localResult1 += eq.eqNumbers[i];
                case '*' -> localResult1 *= eq.eqNumbers[i];
                default -> localResult1 = appendNumber(localResult1, eqNumbers[i]);
            }
        }
        return localResult1 == eq.getEqResult();
    }

    private long appendNumber(long localResult1, long eqNumber) {
        return Long.parseLong("" + localResult1 + eqNumber);
    }

}
