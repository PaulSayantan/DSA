package Searching_Algorithms;

import java.util.Scanner;

/*
Write a java program to implement a Binary Search Algorithm on an array of objects, where object-type is based on user input.
*/
/*
Author: Sayantan Paul
Regd. No: 1841012233
CSE-E 4th Sem 2020
*/
public class BinarySearch<T extends Comparable<T>> {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Integer[] int_arr = {10, 16, 25, 34, 42, 61};
        Integer key = 25;
        BinarySearch<Integer> Integer_Search = new BinarySearch<>();
        Integer index = Integer_Search.B_Search(int_arr, 0, int_arr.length - 1, key);
        System.out.println("Item at index: " + (index + 1));

        //User Input
        Class tClass;
        System.out.println("Enter what type of input array you want to provide: \n1. Integer\n2. Double\n3. String\n4. Character");
        int choice = sc.nextInt();
        if (choice == 1) Class_Array_Binary_search(Integer.class);
        if (choice == 2) Class_Array_Binary_search(Double.class);
        if (choice == 3) Class_Array_Binary_search(String.class);
        if (choice == 4) Class_Array_Binary_search(Character.class);

    }

    private static void Class_Array_Binary_search(Class tClass) {

        System.out.println("Enter size of your input array: ");
        int size = sc.nextInt();
        Integer[] int_arr = new Integer[size];
        Double[] double_arr = new Double[size];
        String[] str_arr = new String[size];
        Character[] char_arr = new Character[size];

        //taking array inputs and key based on class
        if (tClass == Integer.class) {
            System.out.println("Enter Integer Array-Inputs: ");
            for (int i = 0; i < int_arr.length; i++) {
                int_arr[i] = sc.nextInt();
            }
            System.out.println("Enter key: ");
            Integer key = sc.nextInt();


            BinarySearch<Integer> Integer_Search = new BinarySearch<>();
            int index = Integer_Search.B_Search(int_arr, 0, int_arr.length - 1, key);
            System.out.println("Item at index: " + (index + 1));
        }

        if (tClass == Double.class) {
            System.out.println("Enter Double Array-Inputs: ");
            for (int i = 0; i < double_arr.length; i++) {
                double_arr[i] = sc.nextDouble();
            }
            System.out.println("Enter key");
            Double key = sc.nextDouble();

            BinarySearch<Double> Double_Search = new BinarySearch<>();
            int index = Double_Search.B_Search(double_arr, 0, double_arr.length - 1, key);
            System.out.println("Item at index: " + (index + 1));
        }
        if (tClass == String.class) {
            System.out.println("Enter String Array-Inputs: ");
            for (int i = 0; i < str_arr.length; i++) {
                str_arr[i] = sc.next();
            }
            System.out.println("Enter key");
            String key = sc.next();

            BinarySearch<String> Double_Search = new BinarySearch<>();
            int index = Double_Search.B_Search(str_arr, 0, str_arr.length - 1, key);
            System.out.println("Item at index: " + (index + 1));
        }
        if (tClass == Character.class) {
            System.out.println("Enter Character Array-Inputs: ");
            for (int i = 0; i < char_arr.length; i++) {
                char_arr[i] = sc.next().charAt(0);
            }
            System.out.println("Enter key");
            Character key = sc.next().charAt(0);

            BinarySearch<Character> Double_Search = new BinarySearch<>();
            int index = Double_Search.B_Search(char_arr, 0, double_arr.length - 1, key);
            System.out.println("Item at index: " + (index + 1));
        }


    }

    private int B_Search(T[] A, int low, int high, T key) {
        if (low < high) {
            int mid = (high + low) / 2;

            if (key.compareTo(A[mid]) == 0)
                return mid;
            else if (key.compareTo(A[mid]) < 0)
                return B_Search(A, low, mid, key);
            else
                return B_Search(A, mid + 1, high, key);
        } else
            return -1;

    }
}
