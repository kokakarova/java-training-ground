package org.scratchcard;

import lombok.Data;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Data
public class Winnings implements FileReader {

    private int cardValue = 0;
    public static int totalWinnings = 0;

    @Override
    public void readFromFile(String fileName) throws IOException {
        FileReader.super.readFromFile(fileName, "winnings");
    }

    public void processLine(String line) {
        cardValue = 0;
        String splitByColon = line.split(":")[1];
        String[] splitByPipe = splitByColon.split("\\|");
        List<Integer> winningNumbers = getNumbersArray(splitByPipe[0].split(" "));
        List<Integer> numbersYouHave = getNumbersArray(splitByPipe[1].split(" "));
        calculateCardValue(winningNumbers, numbersYouHave);
        totalWinnings += cardValue;
    }

    private List<Integer> getNumbersArray(String[] inputString) {
        return Stream.of(inputString).filter(n -> !n.isEmpty()).mapToInt(Integer::parseInt).boxed().toList();
    }

    private void calculateCardValue(List<Integer> winningNumbers, List<Integer> numbersYouHave) {
        for (Integer number : numbersYouHave) {
            if (!winningNumbers.contains(number)) {
                continue;
            }
            if (winningNumbers.contains(number) && cardValue == 0) {
                cardValue++;
                continue;
            }
            cardValue *= 2;
        }
    }

}
