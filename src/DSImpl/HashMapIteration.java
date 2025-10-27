package DSImpl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapIteration {

    /*
    * 5 ways to iterate on hashmap in java
    * 1. Iterate through a HashMap Entry Set
    * 2. Iterate through a HashMap KeySet using iterator
    * 3. Iterate using Foreach loop
    * 4. Using stream api
    * */

    public void entrySet(Map<String, Integer> m) {

        Iterator<Map.Entry<String, Integer>> iterator = m.entrySet().iterator();

        while(iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public void keySet(Map<String, Integer> m) {
        Iterator<String> iterator = m.keySet().iterator();
        while(iterator.hasNext()) {
            String key = iterator.next();
            System.out.println("Key: " + key + "Value: " + m.get(key));
        }

        //only values

        Iterator<Integer> valueIter = m.values().iterator();
        while(valueIter.hasNext()) {
            System.out.println("Values: " + valueIter.next());
        }

        //Also we can do like this
        for (Map.Entry<String, Integer> entry : m.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }


        for (String key : m.keySet()) {
            System.out.println("Key: " + key + ", Value: " + m.get(key));
        }

        for (Integer value : m.values()) {
            System.out.println("Value: " + value);
        }
    }

    public void forEach (HashMap<String, Integer> m) {
        m.forEach((k,v) -> {
            System.out.println(k);
            System.out.println(v);
        });
    }


    public void stream (HashMap<String, Integer> m) {
        m.entrySet().stream().forEach(entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        });
    }

    //You can get the list of key as follows:

    /*

    keySet() returns a Set<> of keys and we can convert set into keys as follows:

    List<String> keyList = new ArrayList<>(m.keySet());

    opposite is also possible as follows:
    Set<String> set = new HashSet<>(keyList);
     */
}
