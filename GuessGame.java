package com.company;

import java.util.Arrays;
import java.util.Scanner;

//To do: add comments
//Character.isLetter(string.charAt(index))
public class CleanCode {
    private boolean StringToChars(String param) {
        Scanner sc = new Scanner(System.in);
        boolean found = false;
        char[] missingLetters = new char[param.length()];
        int d = Integer.MAX_VALUE;
        char[] buffer = param.toCharArray();
        char[] chars = new char[buffer.length];
        d = chars.length;
        System.out.println("Can you guess the movie ? " + " \n" + "hint: " +
        param.length() + " letters");
        String c = sc.nextLine();
        char[] Cbuffer = c.toCharArray();
        char[] Cchars = new char[param.length()];
        for (int x=0;x<Cchars.length;x++) {
            Cchars[x] = Cbuffer[x];
        }
        System.out.println("each char: ");
        for (int i=0;i<chars.length;i++) {
            chars[i] = buffer[i];
            if(chars[i] == Cchars[i]) {
                d--;
                System.out.print(Cchars[i] + " ");
            } else if (chars[i] != Cchars[i]){
                System.out.print(" _ ");
                missingLetters[i] = chars[i];
                found = false;
//                System.out.println("> " + missingLetters[i] + " <");
            }
            found = (d == 0);
            if (found){System.out.println("100% found WOW!");}
        }

        System.out.print("missing letters: " /*Arrays.toString(missingLetters)*/);
        for(char x:missingLetters){
            if (!Character.isLetter(x)){
                System.out.print("_");
            }else{
                System.out.print(x + " ");
            }
        }
        System.out.println("\nremained " + d + " letters unfound");
//        System.out.println(Arrays.toString(chars));
        return found;
    }
    public static void main(String[] args) {
        CleanCode cleanCode = new CleanCode();
        String movieName = "Hidalgo";

        cleanCode.StringToChars(movieName);
        String x = movieName = "h";

    }
}
