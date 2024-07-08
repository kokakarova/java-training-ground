package org.example;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class MissingParts {

    private HashMap<Integer, Integer> specialChars = new HashMap<>();
    private HashMap<Integer, List<Integer>> numbers = new HashMap<>();

//    public static List<Integer> getNumberIndexes(String line) {
//        System.out.println("line = " + line);
//        System.out.println("line CharArray= " + Arrays.toString(line.toCharArray()));
//        List<Integer> indexes = new ArrayList<>();
//        char[] lineToCharArray = line.toCharArray();
//        for (int i = 0; i < lineToCharArray.length; i++) {
//            if (Character.isDigit(lineToCharArray[i])) {
//                indexes.add(i);
//            }
//        }
//        System.out.println("indexes.list = " + indexes);
//        return indexes;
//    }

    public static int decaMultiplier(int value, int decaValue) {
        // decaValue is 1, 2 (decades), 3(hundreds), 4(thousands) ...
        return (int) (value * Math.pow(10, decaValue - 1));
    }
}
    
