package Domain.Expressions;

import Domain.DataStructures.Dictionary.IDictionary;
import Domain.DataStructures.Dictionary.NotKeyException;
import Domain.DataStructures.Heap.HeapOutOfRangeException;
import Domain.DataStructures.Heap.IHeap;

import java.io.Serializable;

/**
 * Created by Patri on 10/13/2015.
 */
public abstract class Expr implements Serializable {
    public abstract int eval(IDictionary<String, Integer> tbl, IHeap<Integer> heap) throws NotKeyException, DivisionByZeroException, VarNotDefinedException, HeapOutOfRangeException;
    public abstract String toString();
}
