package Atlassian;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
* To achieve all Operations in O(1) we need to take a hashmap and a doubley linked list
* The hashmap will store the key and its corresponding node.
* The node stores the info like freq, A set which includes all the key corresponding to the freq, a pointer next
* a pointer prev
* The map will give me the location of node of respective key in O(1).
* The node has freq and next and prev nodes connected to it.
* The one more important role to the doublely linked list is that it would provide us
* the getMin() and getMax() in O(1), as we have min and max dummy nodes and the nodes connected to these nodes
* will have min and max freq. As the operations like insertion and deletion shifts the key by one step so it is
* easy to achieve using linkedlist in O(1).
* */

public class AllOne {

    private class Node {
        int freq; //Frequency of the keys
        Set<String> keys; //Set of keys with same freq
        Node next; //Node next to max value
        Node prev; //Node next to min value

        public Node(int freq) {
            this.freq = freq;
            keys = new HashSet<>();
        }
    }

    Node min;
    Node max;
    Map<String, Node> keyStore; //Map to store the mapping of keys to their respective frequency node

    public AllOne() {
        keyStore = new HashMap<>();
        min = new Node(-1);
        max = new Node(-1);
        min.next = max;
        max.prev = min;

    }

    /*
    Increase the count of key by 1
    */
    public void inc(String key) {
        if (keyStore.containsKey(key)) {
            //Key exist so remove the key from the current node's keys set
            //and keyStore and shift it to the next node and update the keyStore
            Node curr = keyStore.get(key);
            curr.keys.remove(key);
            keyStore.remove(key);
            /* Always remove the curr node at last otherwise you would lose the ref of curr.next and curr.prev
            and curr.next and curr.prev would be null
            if (curr.keys.isEmpty()) {
                remove(curr);
            }*/

            //Check is there next node with freq + 1 exist or not

            //if exist
            if (curr.next.freq == curr.freq + 1) {
                //add key here and also update the keyStore
                curr.next.keys.add(key);
                keyStore.put(key, curr.next);
            } else {
                //add the new node here
                Node newNode = new Node(curr.freq + 1);
                addRight(curr, newNode); // <--- missing
                newNode.keys.add(key);
                keyStore.put(key, newNode);
            }

            if (curr.keys.isEmpty()) {
                remove(curr);
            }

        } else { //key doesn't exist

            //Check if Node with freq 1 already exist or not

            //if exist
            if (min.next.freq == 1) {
                //add the key here and update keyStore
                min.next.keys.add(key);
                keyStore.put(key, min.next);
            } else {
                //Create a new node and link it and also update keyStore
                Node node = new Node(1);
                addRight(min, node);
                node.keys.add(key);   // ✅ ADD THIS LINE
                keyStore.put(key, node);
            }
        }
    }

    /*
    Decrease the count of key by 1
    */
    public void dec(String key) {
        //if key doesn't exist we won't do anything
        if (!keyStore.containsKey(key))
            return;

        //Key exist
        Node node = keyStore.get(key);

        //The existing node has either freq 1 or more, any ways we have to remove the key from the
        //existing node and keystore in both the cases
        //Remove the key from the node and keyStore
        keyStore.remove(key);
        node.keys.remove(key);



        //freq > 1 and Either prev node exist or not
        //if exist add the node to the prev node
        if (node.prev.freq == node.freq - 1) {
            node.prev.keys.add(key);
            keyStore.put(key, node.prev);
        } else {
            //Create the node and add it to the right of prev node.
            Node newNode = new Node(node.freq - 1);
            addRight(node.prev, newNode);
            newNode.keys.add(key);
            keyStore.put(key, newNode);
        }

        //If freq was 1, after removing key
        //If we keep the node without keys in it will give empty set on getMin, better to remove it
        if (node.keys.isEmpty()) {
            remove(node);
        }
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addRight(Node a, Node b) {
        b.next = a.next;
        b.prev = a;
        a.next = b;
        b.next.prev = b;
    }

    public String getMaxKey() {
        return max.prev == min ? "" : max.prev.keys.iterator().next();
    }

    public String getMinKey() {
        return min.next == max ? "" : min.next.keys.iterator().next();
    }

}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */