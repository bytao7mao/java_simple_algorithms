package com.company;

public class ForThePrimeNumbers {

    // prime numbers are the numbers
    // who divide with 1 and themselves only
    static boolean isPrime(int n){
        for(int i=2;i<n;i++) {
            if(n%i==0)
                return false;
        }
        return true;
    }
    static boolean isPrime3(int n){
        for(int i=2;2*i<n;i++) {
            if(n%i==0)
                return false;
        }
        return true;
    }
    static boolean isPrime2(int n){
        int numerator = 1;
        for(int i=2;i<=n;i++) {
            if(n%i==0)
                numerator++;
        }
        boolean result = numerator > 2 || numerator == 1 ? false : true;
        System.out.println("numerator = " + numerator);
        System.out.println("result =  " + result);
        return result;
    }
    public boolean isPrimeSqrt(int n) {
        if (n <= 1) //get rid of 1 and above
            return false;

        for (int i = 2; i <= Math.sqrt(n); i++) { //sqrt -> all multiple of the same number should be crossed out
            if (n % i == 0)
                return false;
        }
        System.out.println(n + " is prime");
        return true;
    }

}
