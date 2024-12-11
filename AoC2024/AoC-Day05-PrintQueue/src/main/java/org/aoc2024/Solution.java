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

    public void getSolution(String fileName) {
        try (InputStream file = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            boolean savingRulesDone = false;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if (!savingRulesDone && !line.isEmpty()) {
                // save rules in map
//                    System.out.println("saving rules");
//                    System.out.println("line = " + line);
                    saveRulesToHashMap(line);
                }
                if (savingRulesDone && !line.isEmpty()) {
                    // process line
//                    System.out.println("processing updates");
//                    System.out.println("line = " + line);
                }
                if (line.isEmpty()) {
                    savingRulesDone = true;
//                    System.out.println("empty line");
//                    System.out.println("line = " + line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveRulesToHashMap(String line) {
        String[] parts = line.split("[|]");
        Integer num1 = Integer.parseInt(parts[0]);
        Integer num2 = Integer.parseInt(parts[1]);
//        System.out.println("num1 = " + num1);
//        System.out.println("num2 = " + num2);
        if (!rulesMap.containsKey(num1)) {
            List<Integer> rulesList = new ArrayList<>();
            rulesList.add(num2);
            rulesMap.put(num1, rulesList);
        } else {
            rulesMap.get(num1).add(num2);
        }
    }
}
