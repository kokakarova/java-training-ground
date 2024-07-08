package org.example;

import java.util.HashMap;

public class TwoSum {
    // a more efficient solution using a HashMap
    public static int[] twoSumHashMap(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    // my first solution, brute-forcing the solution. Less efficient in time and memory usage
    public static int[] twoSum(int[] num, int target) {
        int[] result = new int[2];
        for (int i = 0; i < ((num.length) - 1); i++) {
            for (int j = 1; j < num.length; j++) {
                if (num[i] + num[j] == target && i != j) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return null;
    }
}
