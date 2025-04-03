package Set;

import java.util.*;

public class Fruits {
    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();

        set.add("apple");
        set.add("banana");
        set.add("orange");
        set.add("apple");

        System.out.println("Set size: " + set.size());

        for (String fruit: set) {
            System.out.println(fruit);
        }
    }
}
