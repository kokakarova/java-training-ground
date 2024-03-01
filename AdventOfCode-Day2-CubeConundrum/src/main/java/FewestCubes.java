import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FewestCubes {

    public static int readPuzzle(String fileName) {
        int result = 1;
        try (InputStream file = FewestCubes.class.getClassLoader().getResourceAsStream(fileName);
        ) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                String[] arrString1 = s.split("[:,;]");
                result *= checkGame(arrString1);
            }
        } catch (NullPointerException | IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return 0;
    }

    public static int checkGame(String[] gameArray) {
        int lowestRed = 1;
        int lowestGreen = 1;
        int lowestBlue = 1;

        for (int i = 1; i < gameArray.length; i++) {
            String[] numAndColorSet = gameArray[i].split(" ");
            System.out.println("numAndColorSet[" + i + "] = " + numAndColorSet[i]);
            if (numAndColorSet[2].contains("red")) {
                lowestRed = checkRed(numAndColorSet[1]);
            }
            if (numAndColorSet[2].contains("green")) {
                lowestGreen = checkGreen(numAndColorSet[1]);
            }
            if (numAndColorSet[2].contains("blue")) {
                lowestBlue = checkBlue(numAndColorSet[1]);
            }
        }
        return lowestRed * lowestGreen * lowestBlue;
    }
    public static int checkRed(String reds) {
        int result = 1;
        return Math.min(result, Integer.parseInt(reds));
    }
    public static int checkGreen(String greens) {
        int result = 1;
        return Math.min(result, Integer.parseInt(greens));
    }
    public static int checkBlue(String blues) {
        int result = 1;
        return Math.min(result, Integer.parseInt(blues));
    }
}

