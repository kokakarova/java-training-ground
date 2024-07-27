package org.boatRace;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BoatRace boatRacePart1 = new BoatRace(List.of(54L, 94L, 65L, 92L), List.of(302L, 1476L, 1029L, 1404L));
        BoatRace boatRacePart2 = new BoatRace(List.of(54946592L), List.of(302147610291404L));
        final long startPart1 = System.currentTimeMillis();
        System.out.println("PART_1 Final result: " + boatRacePart1.getFinalResult());
        final long endPart1 = System.currentTimeMillis();
        System.out.println("The program was running: " + (endPart1-startPart1) + "ms.");
        final long startPart2 = System.currentTimeMillis();
        System.out.println("---------------------------");
        System.out.println("PART_2 Final result: " + boatRacePart2.getFinalResult());
        final long endPart2 = System.currentTimeMillis();
        System.out.println("The program was running: " + (endPart2-startPart2) + "ms.");
    }
}