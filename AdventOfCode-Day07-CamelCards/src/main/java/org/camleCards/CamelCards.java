package org.camleCards;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static org.camleCards.Type.*;

@Data
public class CamelCards {

    TreeMap<String, Integer> highCards = new TreeMap<>();
    TreeMap<String, Integer> onePairs = new TreeMap<>();
    TreeMap<String, Integer> twoPairs = new TreeMap<>();
    TreeMap<String, Integer> kind3s = new TreeMap<>();
    TreeMap<String, Integer> kind4s = new TreeMap<>();
    TreeMap<String, Integer> kind5s = new TreeMap<>();
    TreeMap<String, Integer> fulls = new TreeMap<>();


    public void readFromFile(String fileName, String part) {
        try (InputStream file = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                processLine(s, part);
            }

            int allocatedCards = highCards.size() + onePairs.size()
                    + twoPairs.size() + kind3s.size() + fulls.size()
                    + kind4s.size() + kind5s.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processLine(String line, String part) {
        String[] split = line.split(" ");
        String treeMapStringKey = "";
        Integer bidValue = Integer.parseInt(split[1]);
        List<Character> cardCharsList = convertToList(split[0].toCharArray());
        if (part.equals("part_1")) {
            treeMapStringKey = convertToLettersStringPart1(cardCharsList);
        } else {
            treeMapStringKey = convertToLettersStringPart2(cardCharsList);
        }
        Type type = HIGH_CARD;
        if (part.equals("part_1")) {
            type = getHandTypePart1(cardCharsList);
        } else {
            type = getHandTypePart2(cardCharsList);
        }

        addToListAccordingToType(type, treeMapStringKey, bidValue);
    }

    public String convertToLettersStringPart1(List<Character> handChars) {
        StringBuilder lettersString = new StringBuilder();
        for (Character c : handChars) {
            switch (c) {
                case '2' -> lettersString.append("a");
                case '3' -> lettersString.append("b");
                case '4' -> lettersString.append("c");
                case '5' -> lettersString.append("d");
                case '6' -> lettersString.append("e");
                case '7' -> lettersString.append("f");
                case '8' -> lettersString.append("g");
                case '9' -> lettersString.append("h");
                case 'T' -> lettersString.append("i");
                case 'J' -> lettersString.append("j");
                case 'Q' -> lettersString.append("k");
                case 'K' -> lettersString.append("l");
                case 'A' -> lettersString.append("m");
                default -> lettersString.append('-');
            }
        }
        return lettersString.toString();
    }

    public String convertToLettersStringPart2(List<Character> handChars) {
        StringBuilder lettersString = new StringBuilder();
        for (Character c : handChars) {
            switch (c) {
                case 'J' -> lettersString.append("a");
                case '2' -> lettersString.append("b");
                case '3' -> lettersString.append("c");
                case '4' -> lettersString.append("d");
                case '5' -> lettersString.append("e");
                case '6' -> lettersString.append("f");
                case '7' -> lettersString.append("g");
                case '8' -> lettersString.append("h");
                case '9' -> lettersString.append("i");
                case 'T' -> lettersString.append("j");
                case 'Q' -> lettersString.append("k");
                case 'K' -> lettersString.append("l");
                case 'A' -> lettersString.append("m");
                default -> lettersString.append('-');
            }
        }
        return lettersString.toString();
    }

    private Type getHandTypePart1(List<Character> charList) {

        int removedCards = 0;
        Type type = HIGH_CARD;
        while (!charList.isEmpty()) {
            int fullListSize = charList.size();
            charList.removeAll(List.of(charList.get(0)));
            int subtractedListSize = charList.size();
            removedCards = fullListSize - subtractedListSize;
            switch (removedCards) {
                case 2 -> {
                    switch (type) {
                        case ONE_PAIR -> type = Type.TWO_PAIRS;
                        case THREE_OF_KIND -> type = Type.FULL_HOUSE;
                        default -> type = Type.ONE_PAIR;
                    }
                }
                case 3 -> type = (type == ONE_PAIR) ? Type.FULL_HOUSE : Type.THREE_OF_KIND;
                case 4 -> type = Type.FOUR_OF_KIND;
                case 5 -> type = Type.FIVE_OF_KIND;
                default -> {
                    break;
                }
            }
        }
        return type;
    }

    public Type getHandTypePart2(List<Character> charList) {

        int removedCards = 0;
        int jokers = 0;
        Type type = HIGH_CARD;
        while (!charList.isEmpty()) {
            int fullListSize = charList.size();
            char checkingChar = charList.getFirst();
            charList.removeAll(List.of(checkingChar));
            int subtractedListSize = charList.size();
            removedCards = fullListSize - subtractedListSize;
            if (checkingChar == 'J') {
                if (removedCards == 5) {
                    return FIVE_OF_KIND;
                }
                jokers = removedCards;
                continue;
            }
            switch (removedCards) {
                case 2 -> {
                    switch (type) {
                        case ONE_PAIR -> type = TWO_PAIRS;
                        case THREE_OF_KIND -> type = FULL_HOUSE;
                        default -> type = ONE_PAIR;
                    }
                }
                case 3 -> type = (type == ONE_PAIR) ? FULL_HOUSE : THREE_OF_KIND;
                case 4 -> type = FOUR_OF_KIND;
                case 5 -> type = FIVE_OF_KIND;
                default -> {
                    break;
                }
            }
        }
        ;
        return jokers == 0 ? type : matchJokers(type, jokers);
    }

    public Type matchJokers(Type type, int jokersCount) {
        switch (type) {
            case FOUR_OF_KIND -> {
                return FIVE_OF_KIND;
            }
            case THREE_OF_KIND -> {
                return jokersCount == 2 ? FIVE_OF_KIND : FOUR_OF_KIND;
            }
            case TWO_PAIRS -> {
                return FULL_HOUSE;
            }
            case ONE_PAIR -> {
                return jokersCount == 3 ? FIVE_OF_KIND
                        : jokersCount == 2 ? FOUR_OF_KIND : THREE_OF_KIND;
            }
            default -> {
                return jokersCount == 4 ? FIVE_OF_KIND
                        : jokersCount == 3 ? FOUR_OF_KIND
                        : jokersCount == 2 ? THREE_OF_KIND : ONE_PAIR;
            }
        }
    }

    public List<Character> convertToList(char[] handChars) {
        List<Character> charList = new ArrayList<>();
        for (int i = 0; i < handChars.length; i++) {
            charList.add(handChars[i]);
        }
        return charList;
    }

    public void addToListAccordingToType(Type type, String handString, Integer bid) {
        switch (type) {
            case ONE_PAIR -> onePairs.put(handString, bid);
            case TWO_PAIRS -> twoPairs.put(handString, bid);
            case THREE_OF_KIND -> kind3s.put(handString, bid);
            case FOUR_OF_KIND -> kind4s.put(handString, bid);
            case FIVE_OF_KIND -> kind5s.put(handString, bid);
            case FULL_HOUSE -> fulls.put(handString, bid);
            case HIGH_CARD -> highCards.put(handString, bid);
            default -> throw new IllegalArgumentException("Unknown hand type: " + handString);
        }
    }

    public long calculateWinnings() {
        int rank = 1;
        long winnings = 0;

        for (var c : highCards.keySet()) {
            winnings += (long) highCards.get(c) * rank;
            rank++;
        }

        for (var c : onePairs.keySet()) {
            winnings += (long) onePairs.get(c) * rank;
            rank++;
        }

        for (var c : twoPairs.keySet()) {
            winnings += (long) twoPairs.get(c) * rank;
            rank++;
        }

        for (var c : kind3s.keySet()) {
            winnings += (long) kind3s.get(c) * rank;
            rank++;
        }

        for (var c : fulls.keySet()) {
            winnings += (long) fulls.get(c) * rank;
            rank++;
        }

        for (var c : kind4s.keySet()) {
            winnings += (long) kind4s.get(c) * rank;
            rank++;
        }

        for (var c : kind5s.keySet()) {
            winnings += (long) kind5s.get(c) * rank;
            rank++;
        }
        return winnings;
    }

}

