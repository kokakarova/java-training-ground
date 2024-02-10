package org.example;

import java.util.*;

/**
 * Your goal in this kata is to implement a difference function, which subtracts
 * one list from another and returns the result.
 * <p>
 * It should remove all values from list a, which are present in list b keeping their order.
 * <p>
 * Kata.arrayDiff(new int[] {1, 2}, new int[] {1}) => new int[] {2}
 * If a value is present in b, all of its occurrences must be removed from the other:
 * <p>
 * Kata.arrayDiff(new int[] {1, 2, 2, 2, 3}, new int[] {2}) => new int[] {1, 3}
 */
public class Solution {

    public static int[] arrayDiff(int[] arr1, int[] arr2) {
        if (arr1.length == 0 || arr2.length == 0) {
            return arr1;
        }
        List<Integer> arrList2 = Arrays.stream(arr2).boxed().toList();
        return Arrays.stream(arr1)
                .filter(n -> !(arrList2.contains(n)))
                .toArray();
    }
}
