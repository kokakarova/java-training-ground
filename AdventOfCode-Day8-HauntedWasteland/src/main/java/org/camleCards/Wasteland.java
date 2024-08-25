package org.camleCards;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Data
public class Wasteland {
    char[] directions = "LLRLRRRLLLRLRRLRRRLRLRRLRLRLRRRLRRRLRLRLRRLLRRRLRRLRRLLRLRRRLRLRLLRRRLLRRRLRLRRRLRRRLRRRLLLRRRLRRLRRLRLRRLRLRRRLRLRRLRLRLRRRLRLLLRRRLLLRLRRRLRLRRLRLRLRLRRLRRLRRLRLRRRLRRRLRRLRRRLRRLRRLRRRLLRLRRLLLRRLRRLRLRLLLRRLRRLRRRLRRLLRLRRRLRRRLRRLRRLRLRRLRLRRRLRRLRRRLLRRRLRLRLLLRRRLLLRRLLRRLRLRRLRLLLRRRR".toCharArray();
    //    char[] directions = "RL".toCharArray();
//    char[] directions = "LLR".toCharArray();
//    char[] directions = "LR".toCharArray();
    Map<String, String[]> locationsMap = new HashMap<>();
    List<String> startingNodes = List.of("AAA", "QRA", "KQA", "DFA", "DBA", "HJA");

    public void readFromFile(String fileName, int part) {
        try (InputStream file = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            int lineCounter = 0;
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                processLine(line, part);
                lineCounter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processLine(String line, int part) {
        String location = line.substring(0, 3);
        String left = line.substring(7, 10);
        String right = line.substring(12, line.length() - 1);
        locationsMap.put(location, new String[]{left, right});
    }

    public int findZZZ() {
        int steps = 0;
        int left = 0;
        int right = 1;
        String currentLocation = "AAA";

        for (int i = 0; i < directions.length; i++) {
            steps++;
            currentLocation = directions[i] == 'L' ? locationsMap.get(currentLocation)[left]
                    : locationsMap.get(currentLocation)[right];
            if (currentLocation.equals("ZZZ")) {
                return steps;
            } else if (i == directions.length - 1) {
                i = -1;
            }
        }

        return steps;
    }

    public int find10ZNodes(String node) {
        int steps = 0;
        int left = 0;
        int right = 1;
        String currentLocation = node;
        for (int i = 0; i < directions.length; i++) {
            steps++;
            currentLocation = directions[i] == 'L' ? locationsMap.get(currentLocation)[left]
                    : locationsMap.get(currentLocation)[right];
            if (currentLocation.endsWith("Z")) {
                return steps;
            }
            if (i == directions.length - 1) {
                i = -1;
            }

        }

        return steps;
    }

    public long lcm(long number1, long number2) {
        if (number1 == 0 || number2 == 0) {
            return 0;
        }
        long absNumber1 = Math.abs(number1);
        long absNumber2 = Math.abs(number2);
        long absHigherNumber = Math.max(absNumber1, absNumber2);
        long absLowerNumber = Math.min(absNumber1, absNumber2);
        long lcm = absHigherNumber;
        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber;
        }
        return lcm;
    }

}
