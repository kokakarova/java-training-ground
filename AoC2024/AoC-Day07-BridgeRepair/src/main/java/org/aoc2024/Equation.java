package org.aoc2024;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Equation {
    private int result;
    private List<Integer> values = new ArrayList<>(); // 10+19; 10*19

    public int checkEquation(Equation eq) {
        System.out.println("eq.getResult() = " + eq.getResult());
        int result = -1;
        boolean checkDone = false;
        int startValuesSum = 0;
        int startValuesMulti = 1;

        int i = (eq.values.size() - 1) * 2;
        for (int j = 1; j <= i; j++) {
            if (eq.getResult() == eq.getValues().stream().mapToInt(a -> a).sum()
            || eq.getResult() == multiplyAll(eq.getValues())) {
                result = eq.getResult();
                break;
            }

        }
        System.out.println("result = " + result);
        return result;
    }

    private int multiplyAll(List<Integer> values) {
        int multiplied = 1;
        for (int n: values) {
            multiplied *= n;
        }
        return multiplied;
    }
}
