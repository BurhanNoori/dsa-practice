package Atlassian;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class StockPriceFluctuation  {

    private int currentTime;
    private Map<Integer, Integer> timeToPrice;
    private PriorityQueue<int[]> maxHeap;
    private PriorityQueue<int[]> minHeap;

    public StockPriceFluctuation() {
        this.currentTime = 0;
        this.timeToPrice = new HashMap<>();
        this.maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        this.minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    }

    public void update(int timestamp, int price) {
        timeToPrice.put(timestamp, price);
        maxHeap.offer(new int[]{price, timestamp});
        minHeap.offer(new int[]{price, timestamp});
        currentTime = timestamp;
    }

    //To get the latest price
    public int current() {
        return timeToPrice.get(currentTime);
    }

    //To get the correct max value
    public int maximum() {
        while(true) {
            int[] max = maxHeap.peek();
            if (max[0] == timeToPrice.get(max[1]))
                return max[0];

            maxHeap.poll();
        }

    }

    public int minimum() {
        while(true) {
            int[] min = minHeap.peek();
            if (min[0] == timeToPrice.get(min[1])) {
                return min[0];
            }
            minHeap.poll();
        }
    }
}
