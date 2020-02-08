package com.company;

import java.util.Scanner;

public class CompareAndCapitalizeFirstLetter {
    static Scanner sc = new Scanner(System.in);
    static String goAhead(){
        String result = null;
        int sum = 0;
        System.out.println("Please input two strings: ");
        String one = sc.nextLine(); //use next for one word or
        // nextLine for more words in one line
        String two = sc.next();

        sum = one.length() + two.length();
        System.out.println("sum = " + sum);

        if (one.compareToIgnoreCase(two) > 0) // one higher than two
            System.out.println("one is higher");
        else if (one.compareToIgnoreCase(two) == 0) // equal
            System.out.println("one is equal to two");
        else if (one.compareToIgnoreCase(two) < 0) // two higher than one if negative value
            System.out.println("two is higher");

        //uppercasing the first letter and concatenate with the rest of the string
        String resultOne = one.substring(0,1).toUpperCase()+one.substring(1,one.length());
        String resultTwo = two.substring(0,1).toUpperCase()+two.substring(1,two.length());
        result = resultOne + " " + resultTwo;
        System.out.println(result);
        return result;
    }
}
