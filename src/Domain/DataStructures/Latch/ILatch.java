package Domain.DataStructures.Latch;

import Domain.DataStructures.Heap.HeapOutOfRangeException;

import java.io.Serializable;

/**
 * Created by Patri on 1/22/2016.
 */
public interface ILatch<K, V> extends Serializable {
    K add(V key);
    V get(K key) throws IndexOutOfBoundsException;
    boolean containsKey(K key);
    void update(K key, V value) throws IndexOutOfBoundsException;
}
