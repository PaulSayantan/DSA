package Searching_Algorithms;

import java.util.Arrays;

/*
Write a java program to implement generic Binary Search in iterative style (using while loop).
*/
/*
Author: Sayantan Paul
Regd. No: 1841012233
CSE-E 4th Sem 2020
*/
public class BinarySearch_whileloop<T extends Comparable<T>> {
    public static void main(String[] args) {
        BinarySearch_whileloop<Integer> integerBinarySearch_whileloop = new BinarySearch_whileloop<>();
        Integer[] arr = {10, 32, 43, 61, 51, 63, 73, 61, 12, 78};
        System.out.println("Binary Search always works in Sorted Array.");
        System.out.println("Original Array: \n" + Arrays.toString(arr));
        Arrays.sort(arr);
        System.out.println("The arranged array is: \n" + Arrays.toString(arr));
        int key = 43;
        int index = integerBinarySearch_whileloop.Binary_search_ITERATIVE(arr, key) + 1;
        System.out.println("Item found at index: " + index);
    }

    private int Binary_search_ITERATIVE(T[] arr, T key) {
        int low = 0;
        int high = arr.length;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].compareTo(key) == 0)
                return mid;
            else if (arr[mid].compareTo(key) < 0)
                low = mid + 1;
            else if (arr[mid].compareTo(key) > 0)
                high = mid - 1;
        }

        return -1;
    }
}
