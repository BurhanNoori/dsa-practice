package DSImpl;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap {
    private List<Integer> heap;

    public MaxHeap() {
        this.heap = new ArrayList<>();
    }

    private int parent(int i) {
        return (i-1)/2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2*i + 2;
    }

    private void swap (int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }


    public int peek() {
        return heap.get(0);
    }

    public int poll() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        int max = heap.get(0);
        int last = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, last);
            //heapify down the 0 idx element to fit at proper position
            heapifyDown(0);
        }

        return max;
    }

    private void heapifyDown(int i) {
        int size = heap.size();
        while (i < size) {
            int largest = i;
            int left = leftChild(i);
            int right = rightChild(i);

            if (left < size && heap.get(left) > heap.get(largest)) {
                largest = left;
            }

            if (right < size && heap.get(right) > heap.get(largest)) {
                largest = right;
            }

            if ( i != largest ) {
                swap(i, largest);
                i = largest;
            } else {
                break;
            }
        }
    }

    public void insert(int value) {
        heap.add(value);
        int i = heap.size() - 1;
        heapifyUp(i);
    }

    private void heapifyUp(int i) {
        while ( i > 0 ) {
            int parent = parent(i);
            if (heap.get(i) > heap.get(parent)) {
                swap(i, parent);
                i = parent;
            } else {
                break;
            }
        }
    }
}
