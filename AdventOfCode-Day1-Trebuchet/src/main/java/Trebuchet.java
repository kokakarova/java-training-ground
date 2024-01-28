import java.io.*;


public class Trebuchet {
    public static void main(String[] args) {

        String fileName = "readFile.txt";
        int result = 0;

        try (InputStream file = Trebuchet.class.getClassLoader().getResourceAsStream(fileName)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
//            int[] arrDoubleDigits = new int[0];
            int firstDigit = 0;
            int secondDigit = 0;

            for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                // p8onegjssix
                // 8ninethree7bbjmcnm8
                String digitString = s.replaceAll("[^0-9]", "");
                // 8
                // 878
                if (digitString.length() == 1) {
                    firstDigit = Integer.parseInt(digitString);
                    secondDigit = Integer.parseInt(digitString);
                    result += firstDigit * 10 + secondDigit;
                    // += 88
                } else {
                    firstDigit = Character.getNumericValue(digitString.charAt(0));
                    secondDigit = Character.getNumericValue(digitString.charAt(digitString.length() - 1));
                    result += firstDigit * 10 + secondDigit;
                    // += 88
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(result);

    }

}
