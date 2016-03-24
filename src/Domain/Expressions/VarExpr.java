package Domain.Expressions;

import Domain.DataStructures.Dictionary.IDictionary;
import Domain.DataStructures.Dictionary.NotKeyException;
import Domain.DataStructures.Heap.IHeap;

/**
 * Created by Patri on 10/13/2015.
 */
public class VarExpr extends Expr {
    private String id;

    public VarExpr(String i){

        id = i;
    }

    @Override
    public int eval(IDictionary<String, Integer> tbl, IHeap<Integer> heap) throws NotKeyException, DivisionByZeroException, VarNotDefinedException {
        if(tbl.isKey(id))
            return (int)tbl.getValue(id);

        throw new VarNotDefinedException("Variable not defined!");


    }

    @Override
    public String toString() {
        return id + "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
