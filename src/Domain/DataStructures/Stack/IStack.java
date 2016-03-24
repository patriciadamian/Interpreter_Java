package Domain.DataStructures.Stack;

import java.io.Serializable;

/**
 * Created by Patri on 11/9/2015.
 */
public interface IStack<T> extends Serializable{
    void push(T e);
    T pop() throws EmptyStackException;
    boolean isEmpty();
    //public Object peek();
    String toString();
}

