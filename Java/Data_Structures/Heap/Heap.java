package Data_Structures.Heap;

import java.util.Arrays;

class MaxHeap {

    public int[] heap_array;
    private int curr_pos = 0;

    public MaxHeap(int size) {
        this.heap_array = new int[size];
    }

    public void insert(int data) {
        if (isFull()) {
            throw new RuntimeException("Heap is full !!");
        }

        this.heap_array[++curr_pos] = data;
        heapify_up(curr_pos);
    }

    private void heapify_up(int idx) {
        int parent_idx = (idx - 1) / 2;

        while (parent_idx >= 0 && this.heap_array[parent_idx] < this.heap_array[idx]) {
            int temp = this.heap_array[idx];
            this.heap_array[idx] = this.heap_array[parent_idx];
            this.heap_array[parent_idx] = temp;
            idx = parent_idx;
            parent_idx = (idx - 1) / 2;
        }
    }

    private boolean isFull() {
        return this.curr_pos == this.heap_array.length;
    }
}

public class Heap {
    public static void main(String[] args) {
        MaxHeap myHeap = new MaxHeap(7);
        myHeap.insert(7);
        myHeap.insert(10);
        myHeap.insert(4);
        myHeap.insert(3);
        myHeap.insert(20);
        myHeap.insert(15);

        System.out.println(Arrays.toString(myHeap.heap_array));
    }
}
