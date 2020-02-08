package com.company;

import java.util.Scanner;

public class GuessNumber {
    static Scanner sc = new Scanner(System.in);
    static boolean hasWon = false;

    public static void main(String[] args) {
        int randomNumber = (int) (Math.random() * 100)+1;
        System.out.println("I have randomly chosen a number between 1 and 100");
        System.out.println("Try to guess it: ");

        for (int i=10;i>0;i--){
            System.out.println("You have " + i + " guess(es) left. Choose again");
            int guess = sc.nextInt();

            if (randomNumber < guess){
                System.out.println("It's smaller than " + guess + " guess.");
            }else if (randomNumber > guess){
                System.out.println("It's higher than " + guess + " guess.");
            } else {
                hasWon = true;
                break; //exit the entire for loop
            }
            //System.out.println("Your guess was: " + guess);
        } //end of for loop

        if (hasWon){
            System.out.println("You guess it! you WIN!");
        } else {
            System.out.println("GAME OVER!");
            System.out.println("The number was: " + randomNumber);
        }
    }
}
