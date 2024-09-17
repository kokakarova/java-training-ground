package org.MirageMaintenance;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Mirage mirage = new Mirage();
        Scanner scanner = new Scanner(System.in);
        System.out.println("You're solving AoC 2023 Day 9. Enter:\n1 for Part 1\n2 for Part 2");
        int part = scanner.nextInt();
        final long start = System.currentTimeMillis();
        switch (part) {
            case 1 -> {
                System.out.println("The correct answer for Part 1 should be: "
                        + mirage.readFromFile("puzzle.txt", 1));
            }
            case 2 -> {
                System.out.println("The correct answer for Part 2 should be: "
                        + mirage.readFromFile("puzzle.txt", 2));
            }
            default -> System.out.println("You entered wrong part number");
        }

        final long end = System.currentTimeMillis();
        System.out.println("* * * * * * * * * * * * * * * * ");
        System.out.println("The program was running: " + (end - start) + "ms.");
    }
}

/*
 * PART - 2
 * X -
 * */