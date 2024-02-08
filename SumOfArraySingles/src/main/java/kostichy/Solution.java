package kostichy;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {

    public static int repeats(int[] arr){
        List<Integer> list = new ArrayList<>();
        for (int i : arr) {
            list.add(i);
        }
        return Arrays.stream(arr)
                .filter(n -> Collections.frequency(list, n) == 1)
                .reduce(0, Integer::sum);
    }
}
