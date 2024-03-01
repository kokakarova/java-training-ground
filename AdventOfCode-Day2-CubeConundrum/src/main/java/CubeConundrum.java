import java.io.*;

/**
 * which games would have been possible if the bag contained only
 * 12 red cubes,
 * 13 green cubes, &
 * 14 blue cubes?
 * <p>
 * Game 1:  2 blue, 4 green;
 * 7 blue, 1 red, 14 green;
 * 5 blue, 13 green, 1 red;
 * 1 red, 7 blue, 11 green
 */
public class CubeConundrum {

    private int gameNumber;
    private static final int MAX_RED_CUBES = 12;
    private static final int MAX_GREEN_CUBES = 13;
    private static final int MAX_BLUE_CUBES = 14;

    public static int readPuzzle(String fileName) {
        int result = 0;
        try (InputStream file = CubeConundrum.class.getClassLoader().getResourceAsStream(fileName);
        ) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                String[] arrString1 = s.split("[:,;]");
                result += checkGame(arrString1);
            }
        } catch (NullPointerException | IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return result;
    }

    private static int checkGame(String[] gameSets) {
        int gameNumber = getGameNumber(gameSets[0]);
        boolean okRed = true;
        boolean okGreen = true;
        boolean okBlue = true;
        for (int i = 1; i < gameSets.length; i++) {
            String[] numAndColorSet = gameSets[i].split(" ");
            if (numAndColorSet[2].contains("red") && okRed) {
                okRed = checkRed(numAndColorSet[1]);
            }
            if (numAndColorSet[2].contains("green") && okGreen) {
                okGreen = checkGreen(numAndColorSet[1]);
            }
            if (numAndColorSet[2].contains("blue") && okBlue) {
                okBlue = checkBlue(numAndColorSet[1]);
            }
        }
        if (okRed && okGreen && okBlue) {
            return gameNumber;
        }
        return 0;
    }

    private static boolean checkBlue(String gameSetBlue) {
        int res = Integer.parseInt(gameSetBlue);
        return  (res <= MAX_BLUE_CUBES);
    }

    private static boolean checkGreen(String gameSetGreen) {
        int res = Integer.parseInt(gameSetGreen);
        return (res <= MAX_GREEN_CUBES);
    }

    private static boolean checkRed(String gameSetRed) {
        int res = Integer.parseInt(gameSetRed);
        return (res <= MAX_RED_CUBES);
    }

    public static int getGameNumber(String gameNumberString) {
        return Integer.parseInt(gameNumberString.split(" ")[1]);
    }
}
