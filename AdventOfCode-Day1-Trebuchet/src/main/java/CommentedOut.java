//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//
//public class CommentedOut {
//    private static final String fileName = "readFile.txt";
//    private static int result = 0;
//
//    public static int getResultDigitsAndLetters() {
//
//        try (InputStream file = Trebuchet.class.getClassLoader().getResourceAsStream(fileName)) {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
//
//            int firstDigit = 0;
//            int secondDigit = 0;
//            for (String s = reader.readLine(); s != null; s = reader.readLine()) {
//                for (int i = 0; i < s.length(); i++) {
//                    if (s.charAt(i) == '1'
//                            || s.substring(i, i + 6).contains("one")) {
//                        firstDigit = 1;
//                        break;
//                    }
//                    if (s.charAt(i) == '2'
//                            || s.substring(i, i + 6).contains("two")) {
//                        firstDigit = 2;
//                        break;
//                    }
//                    if (s.charAt(i) == '3'
//                            || s.substring(i, i + 6).contains("three")) {
//                        firstDigit = 3;
//                        break;
//                    }
//                    if (s.charAt(i) == '4'
//                            || s.substring(i, i + 6).contains("four")) {
//                        firstDigit = 4;
//                        break;
//                    }
//                    if (s.charAt(i) == '5'
//                            || s.substring(i, i + 6).contains("five")) {
//                        firstDigit = 5;
//                        break;
//                    }
//                    if (s.charAt(i) == '6'
//                            || s.substring(i, i + 6).contains("six")) {
//                        firstDigit = 6;
//                        break;
//                    }
//                    if (s.charAt(i) == '7'
//                            || s.substring(i, i + 6).contains("seven")) {
//                        firstDigit = 7;
//                        break;
//                    }
//                    if (s.charAt(i) == '8'
//                            || s.substring(i, i + 6).contains("eight")) {
//                        firstDigit = 8;
//                        break;
//                    }
//                    if (s.charAt(i) == '9'
//                            || s.substring(i, i + 6).contains("nine")) {
//                        firstDigit = 9;
//                        break;
//                    }
//                }
//                for (int j = s.length() - 1; j >= 0; j--) {
//                    if (s.charAt(j) == '1'
//                            || s.substring(j, j + 6).contains("one")) {
//                        secondDigit = 1;
//                        break;
//                    }
//                    if (s.charAt(j) == '2'
//                            || s.substring(j, j + 6).contains("two")) {
//                        secondDigit = 2;
//                        break;
//                    }
//                    if (s.charAt(j) == '3'
//                            || s.substring(j, j + 6).contains("three")) {
//                        secondDigit = 3;
//                        break;
//                    }
//                    if (s.charAt(j) == '4'
//                            || s.substring(j, j + 6).contains("four")) {
//                        secondDigit = 4;
//                        break;
//                    }
//                    if (s.charAt(j) == '5'
//                            || s.substring(j, j + 6).contains("five")) {
//                        secondDigit = 5;
//                        break;
//                    }
//                    if (s.charAt(j) == '6'
//                            || s.substring(j, j + 6).contains("six")) {
//                        secondDigit = 6;
//                        break;
//                    }
//                    if (s.charAt(j) == '7'
//                            || s.substring(j, j + 6).contains("seven")) {
//                        secondDigit = 7;
//                        break;
//                    }
//                    if (s.charAt(j) == '8'
//                            || s.substring(j, j + 6).contains("eight")) {
//                        secondDigit = 8;
//                        break;
//                    }
//                    if (s.charAt(j) == '9'
//                            || s.substring(j, j + 6).contains("nine")) {
//                        secondDigit = 9;
//                        break;
//                    }
//                }
//                result += firstDigit * 10 + secondDigit;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//}
