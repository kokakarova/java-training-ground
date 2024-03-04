package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class MissingParts {

    public static List<Integer> getNumberIndexes(String line) {
        System.out.println("line = " + line);
        System.out.println("line CharArray= " + Arrays.toString(line.toCharArray()));
        List<Integer> indexes = new ArrayList<>();
        char[] lineToCharArray = line.toCharArray();
        for (int i = 0; i < lineToCharArray.length; i++) {
            if (Character.isDigit(lineToCharArray[i])) {
                indexes.add(i);
            }
        }
        System.out.println("indexes.list = " + indexes);
        return indexes;
    }
}
//    }
//    public static /*int[][]*/ Object[] getNumberIndexes(String line) {
//        System.out.println("line = " + line);
//        System.out.println("line CharArray= " + Arrays.toString(line.toCharArray()));
//        Object[] testRes = Stream.of(line)
//                .filter(c -> Character.isDigit(c.toCharArray()[0]))
//                .map(c -> c.indexOf(c)).toArray();
//        System.out.println("Arrays.toString(testRes) = " + Arrays.toString(testRes));
//        return testRes;
//    }
    
