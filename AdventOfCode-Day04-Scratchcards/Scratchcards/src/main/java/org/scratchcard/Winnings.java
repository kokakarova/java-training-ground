package org.scratchcard;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

@Data
public class Winnings implements FileReader {

    private int cardValue = 0;
    public static int totalWinnings = 0;
    static HashMap<Integer, Integer> cardsAmount = new HashMap<>();

    @Override
    public void readFromFile(String fileName) {
        FileReader.super.readFromFile(fileName);
    }

    public void processLine(String line, int cardNumber) {
        addCardToMap(cardNumber, 1);
        cardValue = 0;
        String[] splitLine = splitStringToStringArray(line);
        List<Integer> winningNumbers = getNumbersArray(splitLine[0].split(" "));
        List<Integer> numbersYouHave = getNumbersArray(splitLine[1].split(" "));
        int matches = calculateCardValueAndMatches(winningNumbers, numbersYouHave);
        if (matches != 0) {
            int thisCardAmount = cardsAmount.getOrDefault(cardNumber, 1);
            for (int j = 0; j < thisCardAmount; j++) {
                addCardToMap(cardNumber + 1, matches);
            }
        }
        totalWinnings += cardValue;
    }

    private void addCardToMap(int cardNumber, int amountOfCards) {
        for (int i = 0; i < amountOfCards; i++) {
            if (!cardsAmount.containsKey(cardNumber + i)) {
                cardsAmount.put(cardNumber + i, 1);
            } else {
                cardsAmount.put(cardNumber + i, cardsAmount.get(cardNumber + i) + 1);
            }
        }
    }

    private String[] splitStringToStringArray(String line) {
        String splitByColon = line.split(":")[1];
        return splitByColon.split("\\|");
    }

    private List<Integer> getNumbersArray(String[] inputString) {
        return Stream.of(inputString).filter(n -> !n.isEmpty()).mapToInt(Integer::parseInt).boxed().toList();
    }

    private int calculateCardValueAndMatches(List<Integer> winningNumbers, List<Integer> numbersYouHave) {
        int matches = 0;
        for (Integer number : numbersYouHave) {
            if (!winningNumbers.contains(number)) {
                continue;
            }
            matches++;
            if (cardValue == 0) {
                cardValue++;
                continue;
            }
            cardValue *= 2;
        }
        return matches;
    }

    public static int getTotalNumberOfCards() {
        int totalNumberOfCards = 0;
        for (var entry : cardsAmount.entrySet()) {
            totalNumberOfCards += entry.getValue();
        }
        return totalNumberOfCards;
    }
}
