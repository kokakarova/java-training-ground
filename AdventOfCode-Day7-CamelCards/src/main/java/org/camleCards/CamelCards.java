package org.camleCards;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.camleCards.Type.ONE_PAIR;

@Data
public class CamelCards {

    HashMap<String, Integer[]> handsAndBids = new HashMap<>();
    List<String> highCard = new ArrayList<>();
    List<String> onePair = new ArrayList<>();
    List<String> twoPairs = new ArrayList<>();
    List<String> kind3 = new ArrayList<>();
    List<String> kind4 = new ArrayList<>();
    List<String> kind5 = new ArrayList<>();
    List<String> full = new ArrayList<>();


    public void readFromFile(String fileName) {
        try (InputStream file = Main.class.getClassLoader().getResourceAsStream(fileName)) {
            assert file != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                System.out.println("line = " + s);
                processLine(s);
                System.out.println("highCard = " + highCard);
                System.out.println("onePair = " + onePair);
                System.out.println("twoPairs = " + twoPairs);
                System.out.println("kind3 = " + kind3);
                System.out.println("kind4 = " + kind4);
                System.out.println("kind5 = " + kind5);
                System.out.println("full = " + full);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processLine(String line) {
        String[] split = line.split(" ");
        Integer[] bidsAndValues = {Integer.parseInt(split[1]), 0};
        handsAndBids.put(split[0], bidsAndValues);
        sortHandInGroup(split[0]);
//        findHandsValues();
    }

    private void sortHandInGroup(String handString) {
        char[] handChars = handString.toCharArray();
        if (checkForHighCard(handChars)) {
            highCard.add(handString);
            return;
        }
        Type type = findHandType(handChars);
        addToListAccordingToType(type, handString);
    }

    private boolean checkForHighCard(char[] arr) {
        return arr[0] == arr[1] - 1
                && arr[1] == arr[2] - 1
                && arr[2] == arr[3] - 1
                && arr[3] == arr[4] - 1;
    }

    private Type findHandType(char[] handChars) {
        List<Character> charList = convertToList(handChars);
        int listSize = charList.size();
        Type type = Type.NO_WINNINGS;
        while (!charList.isEmpty()) {
            charList.removeAll(List.of(charList.get(0)));
            int adjustedListSize = charList.size();
            switch (listSize - adjustedListSize) {
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
                default -> System.out.println("only 1 char removed");
            }
            listSize = adjustedListSize;
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

    private void addToListAccordingToType(Type type, String handString) {
        switch (type) {
            case ONE_PAIR -> onePair.add(handString);
            case TWO_PAIRS -> twoPairs.add(handString);
            case THREE_OF_KIND -> kind3.add(handString);
            case FOUR_OF_KIND -> kind4.add(handString);
            case FIVE_OF_KIND -> kind5.add(handString);
            case FULL_HOUSE -> full.add(handString);
            default -> System.out.println("Type: " + type);
        }
    }

//    private void findHandsValues() {
//        for (String s : highCard) {
//            getAndAddValue(s);
//        }
//    }

}

