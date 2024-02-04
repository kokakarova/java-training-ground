import java.util.*;
import java.lang.*;

import static java.util.Collections.addAll;

public class SquareOf {

    public static Integer[] squareOf(int n) {
        List<Integer> finalList = new ArrayList<>();
        int counter = n;
        for (int i = 0; i < n; i++) {
            finalList.addAll(0, getArraySection(n, counter));
            counter--;
        }
        Integer[] finalArray = finalList.toArray(new Integer[0]);
        for (Integer num : finalArray) {
            System.out.print(num + ", ");
        }
        return finalArray;
    }

    private static List<Integer> getArraySection(int arrSize, int counter) {
        List<Integer> sectionList = new ArrayList<>();

        for (int i = arrSize; i > 0; i--) {
            if (arrSize > counter){
                sectionList.add(0);
            } else {
                sectionList.add(i);
            }
            counter++;
        }
        return sectionList;
    }
}


