package com.company;

import java.math.BigInteger;


//complexity: O(n)
public class LinearBinarySearching {
    static void linearSearch(int[] arr, int target){
        int result = Integer.MAX_VALUE;
        for (int i=0;i<arr.length;i++){
            if (arr[i] == target){
                result = i;
                System.out.println(arr[i] + " has found at index: " + i);
//                return result;
            }
        }
        System.out.println("no value found");
//        return -1;
    }
    
    //Complexity: O(log n)
    static int binarySearch(int arr[], int x){
        int low = 0;
        int high = arr.length-1;
        int index = Integer.MAX_VALUE;
        while(low<=high){
            int mid = (low + high)/2;
            if (arr[mid]<x){
                low = mid + 1;
                continue;
            } else if (arr[mid]>x) {
                high = mid - 1;
                continue;
            } else if (arr[mid]==x) {
                index = mid;
            break;
            }
        }
        System.out.println(index);
        return index;
    }
    public static void main(String[] args) {
        int[] arr = {17, 20, 33, 40};
        //linearSearch(arr, 11);
//        binarySearch(arr, 33);
//        int bigInteger = 7000;
//        String x = Integer.toBinaryString(bigInteger);
//        int val = Integer.bitCount(bigInteger);
//        System.out.println(val + " <- bitcount" + " -> toBinaryString " + x);
    }
}
