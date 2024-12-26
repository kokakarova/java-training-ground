package org.aoc2024;

import org.apache.commons.lang3.StringUtils;

public class Main {
    public static void main(String[] args) {
        int res = 190;
        int num1 = 10;
        int num2 = 19;
        System.out.println("USING Integer.toBinaryString:");
        System.out.println(res + " in binary -> " + Integer.toBinaryString(res));
        System.out.println(num1 + " in binary -> " + Integer.toBinaryString(num1));
        System.out.println(num2 + " in binary -> " + Integer.toBinaryString(num2));
        System.out.println("---------------");
        System.out.println("USING String.format(\"%8s\", Integer.toBinaryString(n)):");
        System.out.println(res + " in binary -> " + String.format("%8s", Integer.toBinaryString(res)).replace(" ", "0"));
        System.out.println(num1 + " in binary -> " + String.format("%8s", Integer.toBinaryString(num1)).replace(" ", "0"));
        System.out.println(num2 + " in binary -> " + String.format("%8s", Integer.toBinaryString(num2)).replace(" ", "0"));
        System.out.println("---------------");
        System.out.println("USING StringUtils.leftPad(Integer.toBinaryString(n):");
        System.out.println(res + " in binary -> " + StringUtils.leftPad(Integer.toBinaryString(res), 8, "0"));
        System.out.println(num1 + " in binary -> " + StringUtils.leftPad(Integer.toBinaryString(num1), 8, "0"));
        System.out.println(num2 + " in binary -> " + StringUtils.leftPad(Integer.toBinaryString(num2), 8, "0"));
    }
}