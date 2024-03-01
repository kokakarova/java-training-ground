import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

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
    static final String fileName = "puzzleInput.txt";

    public static int readPuzzle(String fileName) {

        InputStream file = CubeConundrum.class.getClassLoader().getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(file));
        int result = 0;
//        final Predicate<String> checkIfIsPossible = (str) -> {
            // if > 12 for Red -> false
            // if > 13 for Green -> false
            // if > 14 for Blue -> false
//            System.out.println(str);
//            if (str.contains("Game")) { return false;}
//
//            var arrSplitString = str.split(" ");
//            Arrays.stream(arrSplitString).forEach(System.out::println);
//            if ((arrSplitString[1].contains("Red")
//                    && Integer.parseInt(arrSplitString[0]) > 12)
//                    || (arrSplitString[1].contains("Green")
//                    && Integer.parseInt(arrSplitString[0]) > 13)
//                    || (arrSplitString[1].contains("Blue")
//                    && Integer.parseInt(arrSplitString[0]) > 14)) {
//
//                return false;
//            }
//            return true;
//        };

        try {
            for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                String[] arrString1 = s.split("[:,;]");
                int i = 0;
                for (String st: arrString1) {
                    System.out.println("arrString1 at position " + i + ": " + st);
                    i++;
                }
//                boolean isPossible = Stream.of(arrString1)
//                        .filter(checkIfIsPossible).isParallel();
//                System.out.println(isPossible);
//                if (isPossible) {
//                    result += Integer.parseInt(arrString1[0].split(" ")[1]);
//                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return 0;

    }

    public static int getGameNumber(String[] gameStringArr) {
        return Integer.parseInt(gameStringArr[0].split(" ")[1]);
    }
}
