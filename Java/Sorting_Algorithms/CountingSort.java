package Sorting_Algorithms;

import java.util.Arrays;

public class CountingSort {
    public static void main(String[] args) throws InterruptedException{
        int[] A = {6, 0, 2, 0, 1, 3, 4, 6, 1, 3, 2};
        int[] B = new int[A.length];
        int[] C = new int[7];
        
        //  filling up empty cells of C
        for (int i = 0; i < C.length; i++) {
            C[i] = 0;
        }
    
        for (int i = 0, k = 1; i < A.length; i++, k++) {
            C[A[i]] = C[A[i]] + 1;
            // System.out.print("After "+ k +"th iteration, C: " + Arrays.toString(C) + "\n");
            // Thread.sleep(1500);
        }

         
        for (int i = 1, k = 1; i < C.length; i++, k++) {
            C[i] = C[i] + C[i - 1];
            // System.out.print("After "+ k +"th iteration, C: " + Arrays.toString(C) + "\n");
            // Thread.sleep(1500);
        }

        //  filling up B array
        for (int i = A.length - 1, k = 1; i >= 0; i--, k++) {
            B[C[A[i]] - 1] = A[i];
            // System.out.print("After "+ k +"th iteration, B: " + Arrays.toString(B) + "\n");
            // Thread.sleep(1000);
            C[A[i]] = C[A[i]] - 1;
            // System.out.print("After "+ k +"th iteration, C: " + Arrays.toString(C) + "\n");
            // Thread.sleep(1000);
        }

        System.out.println("So the sorted array is: " + Arrays.toString(B));
    }
}
