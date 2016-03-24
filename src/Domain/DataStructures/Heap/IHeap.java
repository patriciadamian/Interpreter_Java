package Domain.DataStructures.Heap;

import java.io.Serializable;

/**
 * Created by Patri on 12/7/2015.
 */
public interface IHeap<T> extends Serializable {
    int add(T el);
    T get(int addrress) throws HeapOutOfRangeException;
    void update(int address, T value) throws HeapOutOfRangeException;
    String toString();
}
