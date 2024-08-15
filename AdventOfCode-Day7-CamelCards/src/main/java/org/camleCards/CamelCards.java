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

    //    HashMap<String, Integer[]> handsAndBids = new HashMap<>();
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
            int lineCounter = 0;
            for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                processLine(s, part);
                lineCounter++;
            }
            System.out.println("TOTAL CARDS = " + lineCounter);
            System.out.println(highCards.size() + " highCard");
            System.out.println(onePairs.size() + " onePairs");
            System.out.println(twoPairs.size() + " twoPairs");
            System.out.println(kind3s.size() + " kind3s");
            System.out.println(fulls.size() + " fulls");
            System.out.println(kind4s.size() + " kind4s");
            System.out.println(kind5s.size() + " kind5s");

            int allocatedCards = highCards.size() + onePairs.size()
                    + twoPairs.size() + kind3s.size() + fulls.size()
                    + kind4s.size() + kind5s.size();
            System.out.println("TOTAL CARDS ALLOCATED = " + allocatedCards);
            System.out.println("MISSING CARDS = " + (lineCounter - allocatedCards));
            System.out.println("* * * * * * * * * * * * * * * * * * * * * *");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processLine(String line, String part) {
        String[] split = line.split(" ");
        String treeMapStringKey = "";
        if (part.equals("part_1")) {
            treeMapStringKey = convertToLettersStringPart1(split[0].toCharArray());
        } else {
            treeMapStringKey = convertToLettersStringPart2(split[0].toCharArray());
        }
        Integer bidValue = Integer.parseInt(split[1]);
        List<Character> cardCharsList = convertToList(split[0].toCharArray());
        Type type = getHandTypePart1(cardCharsList);
        addToListAccordingToType(type, treeMapStringKey, bidValue);
    }

    public String convertToLettersStringPart1(char[] handChars) {
        StringBuilder lettersString = new StringBuilder();
        for (char c : handChars) {
            // A, K, Q, J, T, 9, 8, 7, 6, 5, 4, 3, 2
            // m, l, k, j, i, h, g, f, e, d, c, b, a
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
    public String convertToLettersStringPart2(char[] handChars) {
        StringBuilder lettersString = new StringBuilder();
        for (char c : handChars) {
            // A, K, Q, T, 9, 8, 7, 6, 5, 4, 3, 2, J
            // m, l, k, j, i, h, g, f, e, d, c, b, a
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
        // START LIST INSTEAD OF ARRAY HIGHER

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

    private Type getHandTypePart2(List<Character> charList) {
        // START LIST INSTEAD OF ARRAY HIGHER

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

    private List<Character> convertToList(char[] handChars) {
        List<Character> charList = new ArrayList<>();
        for (int i = 0; i < handChars.length; i++) {
            charList.add(handChars[i]);
        }
        return charList;
    }

    private void addToListAccordingToType(Type type, String handString, Integer bid) {
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
//        highCards
//        onePairs
//        twoPairs
//        kind3s
//        fulls
//        kind4s
//        kind5s
//        System.out.println("* * * * * * * * * * * * * * * * *");
//        System.out.println("Entering highCard");
//        System.out.println("highCards.size(): " + highCards.size());
        for (var c : highCards.keySet()) {
            winnings += (long) highCards.get(c) * rank;
            rank++;
        }
//        System.out.println("rank = " + rank);
//        System.out.println("Entering onePairs");
//        System.out.println("onePairs.size(): " + onePairs.size());
        for (var c : onePairs.keySet()) {
            winnings += (long) onePairs.get(c) * rank;
            rank++;
        }
//        System.out.println("rank = " + rank);
//        System.out.println("Entering twoPairs");
//        System.out.println("twoPairs.size(): " + twoPairs.size());
        for (var c : twoPairs.keySet()) {
            winnings += (long) twoPairs.get(c) * rank;
            rank++;
        }

//        System.out.println("rank = " + rank);
//        System.out.println("Entering kind3s");
//        System.out.println("kind3s.size(): " + kind3s.size());
        for (var c : kind3s.keySet()) {
            winnings += (long) kind3s.get(c) * rank;
            rank++;
        }

//        System.out.println("rank = " + rank);
//        System.out.println("Entering fulls");
//        System.out.println("fulls.size(): " + fulls.size());
        for (var c : fulls.keySet()) {
            winnings += (long) fulls.get(c) * rank;
            rank++;
        }

//        System.out.println("rank = " + rank);
//        System.out.println("Entering kind4s");
//        System.out.println("kind4s.size(): " + kind4s.size());
        for (var c : kind4s.keySet()) {
            winnings += (long) kind4s.get(c) * rank;
            rank++;
        }

//        System.out.println("rank = " + rank);
//        System.out.println("Entering kind5s");
//        System.out.println("kind5s.size(): " + kind5s.size());
        for (var c : kind5s.keySet()) {
            winnings += (long) kind5s.get(c) * rank;
            rank++;
        }
        return winnings;
    }

}

