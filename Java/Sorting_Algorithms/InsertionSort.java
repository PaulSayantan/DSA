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
public class InsertionSort<T extends Comparable<T>> {
    public static void main(String[] args) {
        Integer[] int_arr = {10, 20, 35, 40, 13, 42};
        InsertionSort<Integer> IntegerSort = new InsertionSort<>();
        IntegerSort.InsertSort(int_arr);
        System.out.println(Arrays.toString(int_arr));

        String[] str_arr = {"Jose", "Amanda", "Karl", "Shelly", "Brian"};
        InsertionSort<String> StringSort = new InsertionSort<>();
        StringSort.InsertSort(str_arr);
        System.out.println(Arrays.toString(str_arr));

    }

    private void InsertSort(T[] arr) {
        int n = arr.length;
        for (int j = 1; j < n; j++) {
            int i = j;
            T key = arr[j];

            while (i > 0 && less(key, arr[i - 1])) {
                arr[i] = arr[i - 1];
                i--;
            }
            arr[i] = key;
        }
    }
/*
    private void exch(T[] a, int i, int j) {
        T t = a[i]; a[i] = a[j]; a[j] = t;
    }

 */

    private boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }
}
