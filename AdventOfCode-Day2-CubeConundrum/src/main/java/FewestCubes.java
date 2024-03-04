import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FewestCubes {

    public static int readPuzzle(String fileName) {
        int result = 0;
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
            }
            if (gameSet[2].contains("green")) {
                countGreen++;
                if (countGreen == 1) {
                    highestGreen = Integer.parseInt(gameSet[1]);
                } else {
                    highestGreen = Math.max(highestGreen, Integer.parseInt(gameSet[1]));
                }
            }
            if (gameSet[2].contains("blue")) {
                countBlue++;
                if (countBlue == 1) {
                    highestBlue = Integer.parseInt(gameSet[1]);
                } else {
                    highestBlue = Math.max(highestBlue, Integer.parseInt(gameSet[1]));
                }
            }
        }
        return (highestRed * highestGreen * highestBlue);
    }
}

