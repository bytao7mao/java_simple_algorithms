package com.company;

public class ReverseNumber {
    static public int reverse(int n) {
        int result = 0;
        int rem;
        while (n > 0) {
            rem = n % 10;
            n = n / 10;
            result = result * 10 + rem;
        }
        return result;
    }
    static public int reverseS(int n) {
        String input = String.valueOf(n);
        String result = "";
        for (int i = input.length()-1; i >= 0; i--) {
            result = result + input.charAt(i);
        }
        int reversedInt = Integer.parseInt(result);
        return reversedInt;
    }
    static public int reverseSBuilder(int n) {
        String inputString = String.valueOf(n); // make it string
        String stringBuffer = new StringBuffer(inputString).reverse().toString(); //reverse the string
        return Integer.parseInt(String.valueOf(stringBuffer));
    }

    public static void main(String[] args) {
        int x = 12;
        int y = reverseS(12);
        System.out.println(y);
    }
}
