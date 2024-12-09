package org.aoc2024;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class Solution {

    private int sumOfMultiples = 0;

    public void getSolution(String fileName) {
        try (InputStream file = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                processLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processLine(String line) {
        String regex = "[m][u][l][(][\\d]{1,3}[,][\\d]{1,3}[)]";
        List<String> matchedStrings = matchToRegex(regex, line);
        for (String matchedString : matchedStrings) {
            calculateMultiples(matchedString);
        }
    }

    private void calculateMultiples(String substring) {
        String regex = "[\\d]{1,3}";
        List<String> matchedNumbers = matchToRegex(regex, substring);
        sumOfMultiples += Integer.parseInt(matchedNumbers.get(0)) * Integer.parseInt(matchedNumbers.get(1));
    }

    private List<String> matchToRegex(String regex, String stringToExplore) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringToExplore);
        List<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        return matches;
    }


}
