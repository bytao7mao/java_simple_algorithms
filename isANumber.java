package com.CodeFights;

public class IsANumber {
    static boolean isNumeric(String arg){
        boolean isChar = true;
        for (Character c:arg.toCharArray()){
            if (!Character.isDigit(c))
                isChar = false;
        }
        return isChar;
    }
    static int lengthOfInteger(Integer integer){
        int numerator = 0;
        for (Character i:integer.toString().toCharArray()){
            numerator++;
        }
        return numerator;
    }

    public static void main(String[] args) {
        int tribulus = 2234242;
        int lenStringParsing = Integer.toString(tribulus).length(); // first method to find integer length
        int len = (int) (Math.log10(tribulus)+1); // second method
        int lenghtMethod = lengthOfInteger(tribulus);

        System.out.println(
                lenghtMethod +
                " <- from method " + "\n" +
                        "-> from log10 method " +
                len + "\n" + " -> from parsed String = " +
                lenStringParsing); //third method
    }

}

