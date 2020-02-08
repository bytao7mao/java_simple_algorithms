package com.company;

public class EvenOdd {
    static boolean goAhead(int n){
        boolean result = false;
        if (n%2==0)
            result = true;
        else
            result = false;

        System.out.println(result);
        return result;
    }
}
