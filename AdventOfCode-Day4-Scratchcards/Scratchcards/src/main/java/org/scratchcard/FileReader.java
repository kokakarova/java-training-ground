package org.scratchcard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public interface FileReader {

    Winnings winnings = new Winnings();

    default void readFromFile(String fileName, String className) throws IOException {
        try (InputStream file = Winnings.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            int i = 1;
            for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                System.out.println("Line: " + i);
                System.out.println("s = " + s);
                if (className.equals("winnings")) {
                    winnings.processLine(s);
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readFromFile(String fileName) throws IOException;
}
