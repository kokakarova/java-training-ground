/*
Given an array of integers nums and an integer target, return indices of the two numbers such that
they add up to target.
You may assume that each input would have exactly one solution,
and you may not use the same element twice.
You can return the answer in any order.
Example:
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 */
import java.util.*;
import java.util.ArrayList;
import java.lang.Object;
import java.util.Collections;
public class TwoSum {
    public static void main(String[] args) {
        TwoSum holder = new TwoSum();
        int target1 = 9;
        int [] numsArr1 = {2,7,11,15};
        int target2 = 6;
        int [] numsArr2 = {3,2,4};
        int target3 = 6;
        int [] numsArr3 = {3,3};
        int target4 = 13;
        int [] numsArr4 = {1, 8, 3, 9, 5};

        System.out.println("\n1st Check:\n- - - - - - - -");
        holder.getArray(numsArr1);
        System.out.println("Target: " + target1);
        int[] result = holder.getSolution1(numsArr1, target1);
        holder.getArray(result);

        System.out.println("\n2nd Check:\n- - - - - - - -");
        holder.getArray(numsArr2);
        System.out.println("Target: " + target2);
        result = holder.getSolution1(numsArr2, target2);
        holder.getArray(result);

        System.out.println("\n3rd Check:\n- - - - - - - -");
        holder.getArray(numsArr3);
        System.out.println("Target: " + target3);
        result = holder.getSolution1(numsArr3, target3);
        holder.getArray(result);

        System.out.println("\n4th Check:\n- - - - - - - -");
        holder.getArray(numsArr4);
        System.out.println("Target: " + target4);
        result = holder.getSolution1(numsArr4, target4);
        holder.getArray(result);

    }

    public int[] getSolution1(int[] num, int target) {
        int[] result = new int[2];
        for (int i=0; i < ((num.length)-1); i++) {
            for (int j=1; j < num.length; j++) {
                if (num[i]+num[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return null;
    }
//    public int[] getSolution2(int[] arr, int target) {
//        int index1, index2;
//        List<Integer> numbers = new ArrayList<>();
//        // creating a list
//        for (int i : arr) { numbers.add(i); }
//        Collection.sort(numbers);
//        return null;
//    }

    public void getArray(int[] arr) {
        System.out.print("[ ");
        for (int i : arr) System.out.print(i + " ");
        System.out.print("]");
    }

}
