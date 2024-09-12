import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * jjfvnnlfivejj1   -- 11 -- 51
 * 6fourfour        -- 66 -- 64
 * ninevbmltwo69    -- 69 -- 99
 *                  -- 146 -- 214
 */
public class DigitsAndLetters {

    private static final String fileName = "readFile.txt";
    private static int result = 0;

    public static int getResultDigitsAndLetters() {

        try (InputStream file = Trebuchet.class.getClassLoader().getResourceAsStream(fileName)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
//            int i = 1;
            for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                result += getFirstDigit(s) * 10 + getSecondDigit(s);
//                System.out.println("Result Digits and Letters at iteration " + i + ": " + result);
//                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int getFirstDigit(String readLine) {
//        int firstDigit = 0;

        for (int i = 0; i < readLine.length(); i++) {
            if (readLine.startsWith("1", i)
                    || readLine.startsWith("one", i)) {
                return 1;
            }
            if (readLine.startsWith("2", i)
                    || readLine.startsWith("two", i)) {
                return 2;
            }
            if (readLine.startsWith("3", i)
                    || readLine.startsWith("three", i)) {
                return 3;
            }
            if (readLine.startsWith("4", i)
                    || readLine.startsWith("four", i)) {
                return 4;
            }
            if (readLine.startsWith("5", i)
                    || readLine.startsWith("five", i)) {
                return 5;
            }
            if (readLine.startsWith("6", i)
                    || readLine.startsWith("six", i)) {
                return 6;
            }
            if (readLine.startsWith("7", i)
                    || readLine.startsWith("seven", i)) {
                return 7;
            }
            if (readLine.startsWith("8", i)
                    || readLine.startsWith("eight", i)) {
                return 8;
            }
            if (readLine.startsWith("9", i)
                    || readLine.startsWith("nine", i)) {
                return 9;
            }
        }
        return 0;
    }

    public static int getSecondDigit(String readLine) {
//        int secondDigit = 0;
        String reverseString = new StringBuilder(readLine).reverse().toString();
        for (int i = 0; i < reverseString.length(); i++) {
            if (reverseString.startsWith("1", i)
                    || reverseString.startsWith("eno", i)) {
                return 1;
            }
            if (reverseString.startsWith("2", i)
                    || reverseString.startsWith("owt", i)) {
                return 2;
            }
            if (reverseString.startsWith("3", i)
                    || reverseString.startsWith("eerht", i)) {
                return 3;
            }
            if (reverseString.startsWith("4", i)
                    || reverseString.startsWith("ruof", i)) {
                return 4;
            }
            if (reverseString.startsWith("5", i)
                    || reverseString.startsWith("evif", i)) {
                return 5;
            }
            if (reverseString.startsWith("6", i)
                    || reverseString.startsWith("xis", i)) {
                return 6;
            }
            if (reverseString.startsWith("7", i)
                    || reverseString.startsWith("neves", i)) {
                return 7;
            }
            if (reverseString.startsWith("8", i)
                    || reverseString.startsWith("thgie", i)) {
                return 8;
            }
            if (reverseString.startsWith("9", i)
                    || reverseString.startsWith("enin", i)) {
                return 9;
            }
        }
        return 0;
    }
}
