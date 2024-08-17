package org.camleCards;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Data
public class Wasteland {
    char[] directions = "LLRLRRRLLLRLRRLRRRLRLRRLRLRLRRRLRRRLRLRLRRLLRRRLRRLRRLLRLRRRLRLRLLRRRLLRRRLRLRRRLRRRLRRRLLLRRRLRRLRRLRLRRLRLRRRLRLRRLRLRLRRRLRLLLRRRLLLRLRRRLRLRRLRLRLRLRRLRRLRRLRLRRRLRRRLRRLRRRLRRLRRLRRRLLRLRRLLLRRLRRLRLRLLLRRLRRLRRRLRRLLRLRRRLRRRLRRLRRLRLRRLRLRRRLRRLRRRLLRRRLRLRLLLRRRLLLRRLLRRLRLRRLRLLLRRRR".toCharArray();
//    char[] directions = "RL".toCharArray();
//    char[] directions = "LLR".toCharArray();
    Map<String, String[]> locationsMap = new HashMap<>();

    public void readFromFile(String fileName) {
        try (InputStream file = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            int lineCounter = 0;
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                processLine(line);
                lineCounter++;
            }
            System.out.println("directions.length = " + directions.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processLine(String line) {
        String location = line.substring(0, 3);
        String left = line.substring(7, 10);
        String right = line.substring(12, line.length() - 1);
        locationsMap.put(location, new String[]{left, right});
    }

    public int findZZZ() {
        int steps = 0;
        int left = 0;
        int right = 1;
        String currentLocation = "BNR";
//        String currentLocation = "AAA";

        for (int i = 0; i < directions.length; i++) {
            steps++;
            currentLocation = directions[i] == 'L' ? locationsMap.get(currentLocation)[left]
                    : locationsMap.get(currentLocation)[right];
            if (currentLocation.equals("ZZZ")) {
                i = locationsMap.size();
            } else if (i == directions.length - 1) {
                i = 0;
            }
        }

        return steps;
    }


}
