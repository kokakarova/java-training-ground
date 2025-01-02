package org.aoc2024;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Data
public class PuzzleProccessor {

    public String readAntennasFromInput(String fileName) {
        StringBuilder result = new StringBuilder();
        try (InputStream file = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            int i = 0;
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                char c = line.charAt(i);
                result.append(Character.getNumericValue(c) % 2 == 0
                        ? StringUtils.repeat(c, i)
                        : StringUtils.repeat(".", i));
                i++;
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public String addSpaceToString(int i) {
        return StringUtils.repeat(".", i);
    }

    public String addFileToString(int i, char c) {
        return StringUtils.repeat(c, i);
    }
}
