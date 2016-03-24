package Domain.Expressions;

import Domain.DataStructures.Dictionary.IDictionary;
import Domain.DataStructures.Dictionary.NotKeyException;
import Domain.DataStructures.Heap.HeapOutOfRangeException;
import Domain.DataStructures.Heap.IHeap;

/**
 * Created by Patri on 12/7/2015.
 */
public class ReadHeapExpr extends Expr {
    private String id;

    public ReadHeapExpr(String i){
        this.id = i;
    }

    @Override
    public int eval(IDictionary<String, Integer> tbl, IHeap<Integer> heap) throws NotKeyException, DivisionByZeroException, VarNotDefinedException, HeapOutOfRangeException {
        Integer address = (Integer) tbl.getValue(id);
        return heap.get(address);
    }

    @Override
    public String toString() {
        return "readHeap( " + id + " ) ";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
