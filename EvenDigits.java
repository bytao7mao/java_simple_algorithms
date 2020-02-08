package com.CodeFights;

public class EvenDigits {
    static int len = 0;
    static boolean evenDigitsOnly(int n) {
        boolean isEven = true;
        len = (int) (Math.log10(n)+1);
        int[] arr = new int[len];
        for (int i=0;i<len;i++,n = n/10){ // get last digit
            arr[i] = n % 10; // put last digit in arr[i]
        }
        for (int i = 0; i< len;i++){
            if (arr[i]%2!=0) {
                isEven = false;
            }
        }
        return isEven;
    }

    public static void main(String[] args) {
        int x = 24682;
        boolean isOrNot = evenDigitsOnly(x);
        System.out.println(isOrNot);
       // System.out.println(len);
//        int t = 235010;
//        int lenOfT = (int) (Math.log10(t)+1);
//        System.out.println(lenOfT);
    }
}
