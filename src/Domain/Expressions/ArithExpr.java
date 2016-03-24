package Domain.Expressions;

import Domain.DataStructures.Dictionary.IDictionary;
import Domain.DataStructures.Dictionary.NotKeyException;
import Domain.DataStructures.Heap.HeapOutOfRangeException;
import Domain.DataStructures.Heap.IHeap;
import Domain.DataStructures.List.IndexOutOfRangeException;
import Domain.DataStructures.List.IsFullListException;

/**
 * Created by Patri on 10/13/2015.
 */
public class ArithExpr extends Expr{
    private Expr e1;
    private Expr e2;
    private String op;
    /*
    * 1 for +
    * 2 for -
    * 3 for *
    * 4 for /
     */

    public ArithExpr(Expr ex1, Expr ex2, String  opt){
        e1 = ex1;
        e2 = ex2;
        op = opt;


    }

    @Override
    public int eval(IDictionary<String, Integer> tbl, IHeap<Integer> heap) throws NotKeyException, DivisionByZeroException, VarNotDefinedException, HeapOutOfRangeException {
        if(op == "+") return (e1.eval(tbl, heap) + e2.eval(tbl, heap));
        if(op == "-") return (e1.eval(tbl, heap) - e2.eval(tbl, heap));
        if(op == "*") return (e1.eval(tbl, heap) * e2.eval(tbl, heap));
        if(op == "/") {
            if (e2.eval(tbl, heap) != 0) {
                return (e1.eval(tbl, heap) / e2.eval(tbl, heap));
            }
            throw new DivisionByZeroException("Division by zero!");
        }
        return eval(tbl, heap);
    }

    @Override
    public String toString() {
        if (op == "+") return " " + e1.toString() + " + " + e2.toString() + " ";
        if (op == "-") return " " + e1.toString() + " - " + e2.toString() + " ";
        if (op == "*") return " " + e1.toString() + " * " + e2.toString() + " ";
        if (op == "/") return " " + e1.toString() + " / " + e2.toString() + " ";
        return toString();
    }


    public Expr getE1() {
        return e1;
    }

    public void setE1(Expr e1) {
        this.e1 = e1;
    }

    public Expr getE2() {
        return e2;
    }

    public void setE2(Expr e2) {
        this.e2 = e2;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }
}
