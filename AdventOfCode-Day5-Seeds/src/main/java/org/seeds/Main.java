package org.seeds;
import org.seeds.enums.Part;

public class Main {

    public static void main(String[] args) {
        Garden garden = new Garden();
        final long start = System.currentTimeMillis();
        System.out.println(garden.readFromFile("puzzle.txt", Part.PART_1));
        final long end = System.currentTimeMillis();
        System.out.println("The program was running: " + (end-start) + "ms.");
    }
}