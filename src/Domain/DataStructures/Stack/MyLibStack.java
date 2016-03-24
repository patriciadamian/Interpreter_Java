package Domain.DataStructures.Stack;

import Domain.DataStructures.Stack.IStack;

import java.util.Stack;

/**
 * Created by Patri on 11/9/2015.
 */
public class MyLibStack<T> implements IStack<T> {
    private Stack<T> elems;



    public MyLibStack(){

        elems = new Stack<>();
    }

    @Override
    public void push(T e) {
        elems.push(e);

    }

    @Override
    public T pop() throws EmptyStackException{
        if (!this.isEmpty())
            return elems.pop();
        throw new EmptyStackException("Cannot pop! Stack is empty");
    }

    @Override
    public boolean isEmpty() {
        return elems.isEmpty();
    }

    @Override
    public String toString() {
        String res = "";
        for (T elem : elems){
            res = elem.toString() + " " + res;
        }
        return res;
    }
}
