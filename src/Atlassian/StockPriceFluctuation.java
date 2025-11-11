package Atlassian;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
You are given a stream of records about a particular stock.
Each record contains a timestamp and the corresponding price of the stock at that timestamp.

Unfortunately due to the volatile nature of the stock market, the records do not come in order.
Even worse, some records may be incorrect.
Another record with the same timestamp may appear later in the stream correcting the price of the previous wrong record.

Design an algorithm that:

Updates the price of the stock at a particular timestamp, correcting the price from any previous records at the timestamp.
Finds the latest price of the stock based on the current records. The latest price is the price at the latest timestamp recorded.
Finds the maximum price the stock has been based on the current records.
Finds the minimum price the stock has been based on the current records.
Implement the StockPrice class:

StockPrice() Initializes the object with no price records.
void update(int timestamp, int price) Updates the price of the stock at the given timestamp.
int current() Returns the latest price of the stock.
int maximum() Returns the maximum price of the stock.
int minimum() Returns the minimum price of the stock.
========================================================================================================================
                                SOLUTION

Time is something which is continously increasing

we may get the corrected record of past in future so we that was my min or max i need to update it then so my correct max
and min would be something else

As time is keep moving in forward direction i would keep a Map which has key as time and value will be price of stock
Map<time, price>
to get maxPrice at any instant we should have to store the price in a heap so as to get the max and min price in o(1)

in our api is current , update, max, min
To get current time my class should have some field to show the current time

 {
    currentTime: 0,
    map: {time, price},
    maxHeap : maxPrice,
    minHeap: minPrice

 }
 //constructor {
    time = 0
    //init map, maxHeap, minHeap
 }

 update(timestamp, price) {
    recordBook.put(timestamp, price);

    time = Math.max(time, timestamp);
    maxHeap.add(new int{price, timestamp});
    minHeap.add(new int{price, timestamp});

 }
 */

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
        currentTime = Math.max(currentTime, timestamp);
    }

    //To get the latest price
    public int current() {
        return timeToPrice.get(currentTime);
    }

    //To get the correct max value
    public int maximum() {
        while(true) {
            int[] top = maxHeap.peek();
            if (top[0] == timeToPrice.get(top[1]))
                return top[0];

            maxHeap.poll();
        }

    }

    public int minimum() {
        while(true) {
            int[] top = minHeap.peek();
            if (top[0] == timeToPrice.get(top[1]))
                return top[0];

            minHeap.poll();
        }
    }
}
