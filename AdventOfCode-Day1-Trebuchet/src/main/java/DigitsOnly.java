import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DigitsOnly {
    private static final String fileName = "readFile.txt";
    private static int result = 0;

    public static int getResultDigitsOnly() {
        try (InputStream file = Trebuchet.class.getClassLoader().getResourceAsStream(fileName)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));

            for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                String digitString = s.replaceAll("[^0-9]", "");

                int firstDigit = 0;
                int secondDigit = 0;
                if (digitString.length() == 1) {
                    firstDigit = Integer.parseInt(digitString);
                    secondDigit = Integer.parseInt(digitString);
                    result += firstDigit * 10 + secondDigit;
                } else {
                    firstDigit = Character.getNumericValue(digitString.charAt(0));
                    secondDigit = Character.getNumericValue(digitString.charAt(digitString.length() - 1));
                    result += firstDigit * 10 + secondDigit;
                }
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
