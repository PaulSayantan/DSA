package Sorting_Algorithms;



/*
Write a java program
*/
/*
Author: Sayantan Paul
Regd. No: 1841012233
CSE-E 4th Sem 2020
*/

import java.util.Arrays;

public class MergeSort<T extends Comparable<T>> {
    public static void main(String[] args) {
        Integer[] int_arr = {20, 10, 35, 11, 13, 42};
        MergeSort<Integer> IntegerSort = new MergeSort<>();
        IntegerSort.Merge_Sort(int_arr, Integer.class, 0, int_arr.length - 1);
        System.out.println(Arrays.toString(int_arr));

        String[] str_arr = {"Jose", "Amanda", "Karl", "Shelly", "Brian"};
        MergeSort<String> StringSort = new MergeSort<>();
        StringSort.Merge_Sort(str_arr, String.class, 0, str_arr.length - 1);
        System.out.println(Arrays.toString(str_arr));

        Character[] char_arr = {'V', 'A', 'E', 'L', 'Q', 'Z'};
        MergeSort<Character> CharacterSort = new MergeSort<>();
        CharacterSort.Merge_Sort(char_arr, Character.class, 0, char_arr.length - 1);
        System.out.println(Arrays.toString(char_arr));

    }

    private void Merge_Sort(T[] A, Class<T> tclass, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            Merge_Sort(A, tclass, low, mid);
            Merge_Sort(A, tclass, mid + 1, high);
            Merge(A, tclass, low, mid, high);
        }
    }

    private void Merge(T[] A, Class<T> tclass, int low, int mid, int high) {

        int n1 = mid - low + 2;
        int n2 = high - mid + 1;

        @SuppressWarnings("unchecked")
        T[] Left_sub = (T[]) new Comparable[n1];
        @SuppressWarnings("unchecked")
        T[] Right_sub = (T[]) new Comparable[n2];

        for (int i = low; i <= mid; i++) {
            Left_sub[i - low] = A[i];
        }
        for (int j = mid + 1; j <= high; j++) {
            Right_sub[j - mid - 1] = A[j];
        }
        //Defining the infinity value for each class
        if (tclass == Integer.class) {
            Left_sub[n1 - 1] = tclass.cast(Integer.MAX_VALUE);
            Right_sub[n2 - 1] = tclass.cast(Integer.MAX_VALUE);
        }
        if (tclass == Double.class) {
            Left_sub[n1 - 1] = tclass.cast(Double.POSITIVE_INFINITY);
            Right_sub[n2 - 1] = tclass.cast(Double.POSITIVE_INFINITY);
        }
        if (tclass == String.class) {
            Left_sub[n1 - 1] = tclass.cast("ZZZZZZZZZZZZZZZ");
            Right_sub[n2 - 1] = tclass.cast("ZZZZZZZZZZZZZZZZZ");
        }
        if (tclass == Character.class) {
            Left_sub[n1 - 1] = tclass.cast((char) 254);
            Right_sub[n2 - 1] = tclass.cast((char) 254);
        }

        int i = 0, j = 0;
        for (int k = low; k <= high; k++) {
            if (Left_sub[i].compareTo(Right_sub[j]) <= 0) {
                A[k] = Left_sub[i++];
            } else {
                A[k] = Right_sub[j++];
            }
        }


    }

}
