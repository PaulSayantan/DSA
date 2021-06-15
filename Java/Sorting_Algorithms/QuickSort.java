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
public class QuickSort {
    public static void main(String[] args) {
        Integer[] int_arr = {10, 20, 35, 40, 13, 42};
        QuickSort IntegerSort = new QuickSort();
        IntegerSort.quickSort(int_arr, 0, int_arr.length - 1);
        System.out.println(Arrays.toString(int_arr));

        String[] str_arr = {"Jose", "Amanda", "Karl", "Shelly", "Brian"};
        QuickSort StringSort = new QuickSort();
        StringSort.quickSort(str_arr, 0, str_arr.length - 1);
        System.out.println(Arrays.toString(str_arr));

    }

    @SuppressWarnings({"rawtypes"})
    private static int Partition(Comparable[] A, int start, int end) {
        Comparable Pivot = A[end];
        int p_index = (start - 1);

        for (int i = start; i < end; i++) {

            if (less(A[i], Pivot)) {
                p_index++;
                exch(A, i, p_index);
            }
        }
        exch(A, p_index + 1, end);
        return p_index + 1;
    }

    @SuppressWarnings("rawtypes")
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    @SuppressWarnings({"rawtypes"})
    private void quickSort(Comparable[] A, int start, int end) {

        //If list is empty; no need to do anything
        if (A.length <= 1) {
            return;
        }
        if (start < end) {
            int pivot_index = Partition(A, start, end);
            quickSort(A, start, pivot_index - 1);
            quickSort(A, pivot_index + 1, end);
        }

    }
}

