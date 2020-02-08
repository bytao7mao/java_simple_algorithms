package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by tao on 2/8/2020.
 */
public class ReadingFromConsole {
    public static void main(String[] args) {
        //1st way
        BufferedReader console = new BufferedReader(
                new InputStreamReader(System.in));

        //2nd way
        // Using Console to input data from user
//        String name = System.console().readLine();

        //3rd wAY
        // Using Scanner for Getting Input from User
//        Scanner in = new Scanner(System.in);
//        String s = in.nextLine();

        System.out.println("What is your name: ");
        String name = null;
        try {
            name = console.readLine();
        } catch (IOException e) {
            name = "<" + e + ">";//This should never happen
        }
        System.out.println("Hello " + name);
    }
}
