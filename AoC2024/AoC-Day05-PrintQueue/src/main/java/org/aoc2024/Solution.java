package org.aoc2024;

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
public class Solution {
    private Map<Integer, List<Integer>> rulesMap = new HashMap<>();
    private Integer sumOfMiddles = 0;

    public void getSolution(String fileName, int partNumber) {
        try (InputStream file = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            boolean savingRulesDone = false;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if (!savingRulesDone && !line.isEmpty()) {
                    saveRulesToHashMap(line);
                }
                if (savingRulesDone && !line.isEmpty()) {
                    int[] updateArray = getIntArray(line);
                    System.out.println("line = " + line);
                    boolean inRightOrder = checkOrder(updateArray);
                    if (inRightOrder && partNumber == 1) {
                        int len = updateArray.length;
                        sumOfMiddles += updateArray[len / 2];
                    } else if (partNumber == 2 && !inRightOrder) {
                        fixOrder(updateArray);
                    }
                }
                if (line.isEmpty()) {
                    savingRulesDone = true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int[] getIntArray(String line) {
        String[] updateArray = line.split(",");
        int[] updateArrayInt = new int[updateArray.length];
        for (int i = 0; i < updateArrayInt.length; i++) {
            updateArrayInt[i] = Integer.parseInt(updateArray[i]);
        }
        return updateArrayInt;
    }

    private boolean checkOrder(int[] updateArray) {
        int len = updateArray.length;
        for (int i = 0; i < len; i++) {
            int num1 = updateArray[i];
            for (int j = i + 1; j < len; j++) {
                int num2 = updateArray[j];
                if (isInWrongOrder(num1, num2)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void fixOrder(int[] updateArray) {
        int len = updateArray.length;
        int[] newOrder = updateArray.clone();
        int i, j;
        boolean inOrder = false;
        while (!inOrder) {
            for (i = 0; i < len - 1; i++) {
                if (isInWrongOrder(newOrder[i], newOrder[i + 1])) {
                    int transit = newOrder[i];
                    newOrder[i] = newOrder[i + 1];
                    newOrder[i + 1] = transit;
                }
            }
            inOrder = checkOrder(newOrder);
        }
        sumOfMiddles += newOrder[len / 2];
    }

    private boolean isInWrongOrder(Integer num1, Integer num2) {
        return (rulesMap.containsKey(num1) && !rulesMap.get(num1).contains(num2))
                || (rulesMap.containsKey(num2) && rulesMap.get(num2).contains(num1));
    }

    private void saveRulesToHashMap(String line) {
        String[] parts = line.split("[|]");
        Integer num1 = Integer.parseInt(parts[0]);
        Integer num2 = Integer.parseInt(parts[1]);
        if (!rulesMap.containsKey(num1)) {
            List<Integer> rulesList = new ArrayList<>();
            rulesList.add(num2);
            rulesMap.put(num1, rulesList);
        } else {
            rulesMap.get(num1).add(num2);
        }
    }
}
