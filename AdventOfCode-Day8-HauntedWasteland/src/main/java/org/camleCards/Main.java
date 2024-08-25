package org.camleCards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Wasteland w = new Wasteland();
        List<Integer> allSteps = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("You're solving AoC 2023 Day 8. Enter:\n1 for Part 1\n2 for Part 2");
        int part = scanner.nextInt();
        final long start = System.currentTimeMillis();
        switch (part) {
            case 1 -> {
                w.readFromFile("puzzle.txt", 1);
                System.out.println("ZZZ was found in " + w.findZZZ() + " steps");
            }
            case 2 -> {
                w.readFromFile("puzzle.txt", 2);
                List<String> startingNodes = List.of("AAA", "QRA", "KQA", "DFA", "DBA", "HJA");
                for (String node : startingNodes) {
                    allSteps.add(w.find10ZNodes(node));
                }
                long lcm = allSteps.get(0);
                for (int i = 1; i < allSteps.size(); i++) {
                    lcm = w.lcm(lcm, (long) allSteps.get(i));
                }
                System.out.println("Answer to Part 2 is " + lcm);
            }
            default -> System.out.println("You entered wrong part number");
        }

        final long end = System.currentTimeMillis();
        System.out.println("* * * * * * * * * * * * * * * * ");
        System.out.println("The program was running: " + (end - start) + "ms.");
    }
}