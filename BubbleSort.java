package com.company;

import java.util.Arrays;

public class BubbleSort  {
    public static int[] bubbleSort(int arr[]) {
        //from the first digit to the last digit from the array
        for (int i = 0; i < arr.length; i++) {
            //comparing all digits x times length of the array - 1
            //because we have to get rid of the actual digit that 
            //we are comparing with other digits
            for (int j = 0; j < arr.length-1; j++) {
                //if statement is true we swap digits
                if(arr[j]>arr[j+1]) {
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        return arr;
    }
}
