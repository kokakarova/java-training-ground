package org.scratchcard;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Winnings implements FileReader {

    @Override
    public void readFromFile(String fileName) throws IOException {
        FileReader.super.readFromFile(fileName, "winnings");
    }

    public void processLine(String line) {
        String splitByColon = line.split(":")[1];
        String[] splitByPipe = splitByColon.split("\\|");
        int[] winningNumbers = getNumbersArray(splitByPipe[0].split(" "));
        System.out.println("winningNumbers = " + Arrays.toString(winningNumbers));
        int[] numbersYouHave = getNumbersArray(splitByPipe[1].split(" "));
        System.out.println("numbersYouHave = " + Arrays.toString(numbersYouHave));
    }

    private int[] getNumbersArray(String[] inputString) {
        return Stream.of(inputString).filter(n -> !n.isEmpty()).mapToInt(Integer::parseInt).toArray();
    }

}
