package Domain.DataStructures.List;

import Domain.DataStructures.List.IList;

import java.util.ArrayList;

/**
 * Created by Patri on 11/9/2015.
 */
public class MyLibList<T> implements IList<T> {
    private ArrayList<T> list;

    public MyLibList(){
        list = new ArrayList<>();
    }




    @Override
    public void add(T e) throws IsFullListException{
        /*
        The initial capacity of a list is 10
         */
        if(this.list.size() == 10) throw new IsFullListException("Full List!");
        list.add(e);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public T getElemAtPos(int i) throws IndexOutOfRangeException{
        if (i >= 0 && i < list.size())
            return list.get(i);
        throw  new IndexOutOfRangeException("Index out of range in list!");
    }

    @Override
    public int size() {
        return list.size();
    }


    public String toString(){
        return " " + list + " ";
    }

    public boolean contains(T e){
        return list.contains(e);
    }
}
