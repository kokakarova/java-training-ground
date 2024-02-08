package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr1 = {1, 2};
        int[] arr2 = {1, 2};
    /*-------------------------------------*/
        int[] arr10 = {1, 2, 2};
        int[] arr20 = {1};
    /*-------------------------------------*/
        int[] arr100 = {1, 2, 2};
        int[] arr200 = {2};

    /*-------------------------------------*/
        int[] arr110 = {};
        int[] arr220 = {2};

    /*-------------------------------------*/
        int[] arr111 = {1, 2};
        int[] arr222 = {};

        int[] result1 = Solution.arrayDiff(arr1, arr2);
        System.out.println(Arrays.toString(result1));

        int[] result10 = Solution.arrayDiff(arr10, arr20);
        System.out.println(Arrays.toString(result10));

        int[] result100 = Solution.arrayDiff(arr100, arr200);
        System.out.println(Arrays.toString(result100));

        int[] result110 = Solution.arrayDiff(arr110, arr220);
        System.out.println(Arrays.toString(result110));

        int[] result111 = Solution.arrayDiff(arr111, arr222);
        System.out.println(Arrays.toString(result111));
    }
}