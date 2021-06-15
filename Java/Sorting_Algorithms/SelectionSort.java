package Sorting_Algorithms;

import java.util.Arrays;

/*
Write a java program
*/
/*
Author: Sayantan Paul
Regd. No: 1841012233
CSE-E 4th Sem 2020
*/
public class SelectionSort {
    public static void main(String[] args) {
        Integer[] int_arr = {10, 20, 35, 40, 13, 42};
        selectionSort(int_arr);
        System.out.println(Arrays.toString(int_arr));

        String[] str_arr = {"Jose", "Amanda", "Karl", "Shelly", "Brian"};
        selectionSort(str_arr);
        System.out.println(Arrays.toString(str_arr));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void selectionSort(Comparable[] A) {
        for (int i = 0; i < A.length; i++) {
            int min = i;
            for (int j = i + 1; j < A.length; j++) {
                if (A[j].compareTo(A[min]) < 0) {
                    min = j;
                }
            }
            if (min != i) {
                swap(A, i, min);
            }
        }
    }

    @SuppressWarnings({"rawtypes"})
    private static void swap(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
