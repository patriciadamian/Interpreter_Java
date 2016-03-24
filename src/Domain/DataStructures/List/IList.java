package Domain.DataStructures.List;

import java.io.Serializable;

/**
 * Created by Patri on 11/9/2015.
 */
public interface IList<T> extends Serializable {
    void add(T e) throws IsFullListException;
    boolean isEmpty();
    T getElemAtPos(int i) throws IndexOutOfRangeException;
    int size();
    String toString();
    boolean contains(T e);
}
