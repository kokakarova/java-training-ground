import java.util.LinkedList;
import java.util.Stack;

import static java.util.Arrays.binarySearch;
/*
    Given two strings s and t, return true if they are equal when both are typed
    into empty text editors. '#' means a backspace character.
    Note that after backspacing an empty text, the text will continue empty.
    Example 1:
    Input: s = "ab#c", t = "ad#c"
    Output: true
    Explanation: Both s and t become "ac".
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("ab#c -> ac\nad#c -> ac\nshould be true. Answer: "
                + solution.backspaceCompare("ab#c", "ad#c"));
        System.out.println("ab## -> <empty>\nc#d# -> <empty>\nshould be true. Answer: "
                + solution.backspaceCompare("ab##", "c#d#"));
        System.out.println("a#c -> c\nb -> b\nshould be false. Answer: "
                + solution.backspaceCompare("a#c", "b"));
    }
    public boolean backspaceCompare(String s, String t) {
        Stack<Character> strS = new Stack<>();
        Stack<Character> strT = new Stack<>();
        // cleaning string s and converting it to stack
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) {
                strS.push(s.charAt(i));
            }
            if (!Character.isLetter(s.charAt(i))) {
                strS.pop();
            }
        }
        // cleaning string t and converting it to stack
        for (int i = 0; i < t.length(); i++) {
            if (Character.isLetter(t.charAt(i))) {
                strT.push(t.charAt(i));
            }
            if (!Character.isLetter(t.charAt(i))) {
                strT.pop();
            }
        }
        // converting stack strS back to string
        s = "";
        if (strS.size() > 0) {
            for (int c = strS.size()-1; c >= 0; c--) {
                s += strS.peek();
            }
            //s = s.substring(2);
        }
        // converting stack strT back to string t
        t = "";
        if (strT.size() > 0) {
            for (int c = strT.size()-1; c >= 0; c--) {
                t += strT.peek();
            }
            //t = t.substring(2);
        }
        return s.equals(t);
    }
}
