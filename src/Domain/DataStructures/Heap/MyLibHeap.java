package Domain.DataStructures.Heap;

import java.util.HashMap;

/**
 * Created by Patri on 12/7/2015.
 */
public class MyLibHeap<T> implements  IHeap<T> {
    private HashMap<Integer, T> elems;
    private int next;

    public HashMap<Integer, T> getElems() {
        return elems;
    }

    public void setElems(HashMap<Integer, T> elems) {
        this.elems = elems;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }





    public MyLibHeap(){
        elems = new HashMap<>();
        next = 1;
    }
    @Override
    public int add(T el) {
        elems.put(next, el);
        return next++;
    }

    @Override
    public T get(int addrress) throws HeapOutOfRangeException{
        if (elems.get(addrress) == null){
            throw new HeapOutOfRangeException();
        }
        return  elems.get(addrress);
    }

    @Override
    public void update(int address, T value) throws  HeapOutOfRangeException {
        if (elems.get(address) == null) {
            throw new HeapOutOfRangeException();
        }
        elems.put(address, value);
    }


    public String toString(){
        String res = "";
        for(Integer el : elems.keySet()){
            res  = el.toString() + "->" + elems.get(el) + " " + res ;
        }
        return res;
    }

}
