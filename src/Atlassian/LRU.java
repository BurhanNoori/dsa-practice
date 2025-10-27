package Atlassian;

import java.util.HashMap;
import java.util.Map;

public class LRU {
    private class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }


    Node right;
    Node left;
    Map<Integer, Node> cache;
    int CAPACITY;

    public LRU(int capacity) {
        this.CAPACITY = capacity;
        cache = new HashMap<>();
        left = new Node(0, 0);
        right = new Node(0, 0);
        /*left is for LRU and right is for MRU*/
        left.next = right;
        right.prev = left;

    }

    //Removing the node from the doubly linkedlist
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    //Inserting the node at right side of the doubly linkedlist
    private void insert(Node node) {
        node.prev = right.prev;
        node.next = right;
        node.prev.next = node;
        right.prev = node;
    }

    public int get(int key) {
        if(!cache.containsKey(key)) return -1;
        Node node = cache.get(key);
        remove(node);
        insert(node);
        return node.value;
    }

    public void put(int key, int value) {
        if(cache.containsKey((key))) {
            Node node = cache.get(key);
            node.value = value;
            remove(node);
            insert(node);
            return;
        }
        if (CAPACITY == cache.size()) {
            Node lru = left.next;
            remove(lru);
            cache.remove(lru.key);
        }
        Node node = new Node(key, value);
        cache.put(key, node);
        insert(node);
    }


}
