package lab13.zad1.hashset;

import java.util.HashSet;
import java.util.Set;

public class MyHashSet {

    private Set<Integer> set = new HashSet<>();

    public synchronized void add(Integer value) {
        set.add(value);
        System.out.println("Add: " + value);
    }

    public synchronized void remove(Integer value) {
        set.remove(value);
    }

    @Override
    public String toString() {
        return "MyHashSet{" +
                "set=" + set.toString() +
                '}';
    }
}
