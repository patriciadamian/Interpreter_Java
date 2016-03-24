package Domain.Expressions;

import Domain.DataStructures.Dictionary.IDictionary;
import Domain.DataStructures.Heap.IHeap;

/**
 * Created by Patri on 11/10/2015.
 */
public class ReadExpr extends Expr {
    private Integer nr;

    public ReadExpr(Integer n){
        this.nr = n;
    }


    @Override
    public int eval(IDictionary<String, Integer> tbl, IHeap<Integer> heap) {
        return nr;
    }

    @Override
    public String toString() {
        return " read()";
    }

    public Integer getNr() {
        return nr;
    }

    public void setNr(Integer nr) {
        this.nr = nr;
    }
}
