import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//game1: green 14, blue 7, red 1 = 98
//game2: green 3, blue 7, red 4 = 84
//game3: green 12, blue 10, red 6 = 720
//        = 902

public class FewestCubes {

    public static int readPuzzle(String fileName) {
        int result = 1;
        try (InputStream file = FewestCubes.class.getClassLoader().getResourceAsStream(fileName);
        ) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                String[] gameLine = s.split("[:,;]");
                result += checkGame(gameLine);
            }
        } catch (NullPointerException | IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return result;
    }

    public static int checkGame(String[] gameArray) {
        int highestRed = 1;
        int highestGreen = 1;
        int highestBlue = 1;
        int countRed = 0;
        int countGreen = 0;
        int countBlue = 0;

        for (int i = 0; i < gameArray.length; i++) {
            String[] gameSet = gameArray[i].split(" ");
//            System.out.println("gameLine length= " + gameArray.length);
//            System.out.println("i = " + i + " Game Array at i = " + gameArray[i]);
//            System.out.println("gameSet = " + Arrays.toString(gameSet));
//            System.out.println("numAndColorSet[" + i + "] = " + gameSet[i]);
            if (i == 0) {
                continue;
            }
            if (gameSet[2].contains("red")) {
                countRed++;
                if (countRed == 1) {
                    highestRed = Integer.parseInt(gameSet[1]);
                } else {
                    highestRed = Math.max(highestRed, Integer.parseInt(gameSet[1]));
                }
//                System.out.println("lowestRed = " + lowestRed);
            }
            if (gameSet[2].contains("green")) {
                countGreen++;
                if (countGreen == 1) {
                    highestGreen = Integer.parseInt(gameSet[1]);
                } else {
                    highestGreen = Math.max(highestGreen, Integer.parseInt(gameSet[1]));
                }
//                System.out.println("lowestGreen = " + lowestGreen);
            }
            if (gameSet[2].contains("blue")) {
                countBlue++;
                if (countBlue == 1) {
                    highestBlue = Integer.parseInt(gameSet[1]);
                } else {
                    highestBlue = Math.max(highestBlue, Integer.parseInt(gameSet[1]));
                }
//                System.out.println("highestBlue = " + highestBlue);
            }
        }
//        System.out.println("lowestRed = " + lowestRed);
//        System.out.println("lowestGreen = " + lowestGreen);
//        System.out.println("highestBlue = " + highestBlue);
        return (highestRed * highestGreen * highestBlue);
    }

//    public static int checkRed(String reds) {
//        System.out.println("reds = " + reds);
//        int result = 1;
//        return Math.min(result, Integer.parseInt(reds));
//    }
//
//    public static int checkGreen(String greens) {
//        int result = 1;
//        return Math.min(result, Integer.parseInt(greens));
//    }
//
//    public static int checkBlue(String blues) {
//        System.out.println("blues = " + blues);
//        int result = 1;
//        return Math.min(result, Integer.parseInt(blues));
//    }
}

