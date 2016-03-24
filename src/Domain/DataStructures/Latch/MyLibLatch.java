package Domain.DataStructures.Latch;

import Domain.DataStructures.Heap.HeapOutOfRangeException;
import Domain.DataStructures.List.IList;

import java.util.HashMap;

/**
 * Created by Patri on 1/22/2016.
 */

public class MyLibLatch<V> implements ILatch<Integer, V> {
    private HashMap<Integer, V> elements;
    private int nextFree;

    public MyLibLatch() {
        elements = new HashMap<>();
        nextFree = 1;
    }

    @Override
    public Integer add(V e) {
        elements.put(nextFree, e);
        return nextFree++;
    }

    @Override
    public synchronized V get(Integer key) throws IndexOutOfBoundsException {
        if (elements.get(key) == null) {
            throw new IndexOutOfBoundsException();
        }
        return elements.get(key);
    }

    @Override
    public boolean containsKey(Integer key) {
        return elements.get(key) != null;
    }

    @Override
    public synchronized void update(Integer key, V value) throws IndexOutOfBoundsException {
        if (elements.get(key) == null) {
            throw new IndexOutOfBoundsException();
        }
        elements.put(key, value);
    }

    @Override
    public String toString() {
        String toPrint = "";
        for (Integer elem : elements.keySet()) {
            toPrint = elem.toString() + " -> " + elements.get(elem)  + "\n" + toPrint;
        }
        return toPrint;
    }
}
