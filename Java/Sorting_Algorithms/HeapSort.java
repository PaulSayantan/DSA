package Sorting_Algorithms;

import java.util.Arrays;

/*
Write a java program to perform heapsort on an array.
*/
/*
Author: Sayantan Paul
Regd. No: 1841012233
CSE-E 4th Sem 2020
*/

public class HeapSort<T extends Comparable<T>> {
    
    public static void main(String[] args) {
        Integer[] int_arr = {10, 20, 35, 40, 13, 42};
        HeapSort<Integer> IntegerSort = new HeapSort<>();
        IntegerSort.sort(int_arr);
        System.out.println(Arrays.toString(int_arr));
        
        String[] str_arr = {"Jose", "Amanda", "Karl", "Shelly", "Brian"};
        HeapSort<String> StringSort = new HeapSort<>();
        StringSort.sort(str_arr);
        System.out.println(Arrays.toString(str_arr));
    }
    
    private void Heapify(T[] arr, int n, int i) {
        int large = i;
        int left = 2 * i + 1;
        int right = 2 * i - 2;

        if (left < n && less(arr[large], arr[left])) {
            large = left;
        }

        if (right < n && less(arr[large], arr[right])) {
            right = left;
        }

        if (large != i) {
            exch(arr, i, large);

            Heapify(arr, n, large);
        }

    }

    private void sort(T[] arr) {
        int n = arr.length;

        for (int i = n/2 - 1; i >= 0; i--) {
            Heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            exch(arr, 0, i);
            Heapify(arr, i, 0);
        }

    }

    private void exch(T[] a, int i, int j) {
        T t = a[i]; a[i] = a[j]; a[j] = t;
    }

    private boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }
}