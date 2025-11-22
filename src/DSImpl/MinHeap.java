package DSImpl;

import java.util.ArrayList;
import java.util.List;

public class MinHeap {

    private List<Integer> heap;


    public MinHeap() {
        heap = new ArrayList<>();
    }


    private int parent(int i) {
        return (i-1)/2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i){
        return 2*i + 2;
    }

    private void swap (int i, int j) {
        int temp = heap.get(i);
        heap.set(i,heap.get(j));
        heap.set(j, temp);
    }

    public int peek() {
        return heap.get(0);
    }


    public void poll() {
        int min = heap.get(0);
        int last = heap.get(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, last);
            //heapify down the 0 idx element to fit at proper position
           heapifyDown(0);
        }
    }
    /*
    It expects that the heap is heapified down the i.
    That's why it is recommended to be done from last
    The heapify up doesn't consider sibling value because
    as soon as you add the node it would be either left or right
    you start heapify up. So if the node is added to right the heap
    is already heapified when left was added. So now the concern is to
    just check the node and its parent unlike heapifyDown
    */
    private void heapifyUp(int i) {

        while(i > 0 ){
            int parent = parent(i);
            if (heap.get(i) < heap.get(parent)) {
                swap(i, parent);
                i = parent;
            } else {
                break;
            }
        }
    }

    /*
    It expects that the heap is heapified from up the i.
    That's why it is recommended to be done from root
    The heapifyDown consider the min among the siblings
    */
    private void heapifyDown(int i) {
        int size = heap.size();

        while ( i < size ) {
            int smallest = i;
            int left = leftChild(i);
            int right = rightChild(i);

            if (left < size && heap.get(left) < heap.get(smallest)) {
                smallest = left;
            }

            if (right < size && heap.get(right) < heap.get(smallest)) {
                smallest = right;
            }

            if (i != smallest) {
                swap(i, smallest);
                i = smallest;
            } else {
                break;
            }

        }

    }

    public void insert(int value) {
        //adding at the last
        heap.add(value);
        int idx = heap.size() - 1;

        //Heapify up
        while(idx > 0 && value < heap.get(parent(idx))) {
            swap(idx, parent(idx));
            idx = parent(idx);
        }
    }



}
