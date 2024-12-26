package org.aoc2024;


public class Main {
    public static void main(String[] args) {
        String fileName = "puzzle.txt";
        InputProcessor ip = new InputProcessor();
        ip.readEquationsFromInput(fileName);
        System.out.println("Part 1 RESULT ---> " + Equation.validEquations);
    }
}