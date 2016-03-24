package Domain.Expressions;

import Domain.DataStructures.Dictionary.IDictionary;
import Domain.DataStructures.Dictionary.NotKeyException;
import Domain.DataStructures.Heap.HeapOutOfRangeException;
import Domain.DataStructures.Heap.IHeap;

/**
 * Created by Patri on 11/9/2015.
 */
public class BoolExpr extends Expr {
    private String op;
    private Expr e1;
    private Expr e2;

    /*
    * <
    * >
    * <=
    * >=
    * ==
    * !=
     */


    public BoolExpr(Expr ex1, Expr ex2, String o){
        this.op = o;
        this.e1 = ex1;
        this.e2 = ex2;

    }

    @Override
    public int eval(IDictionary<String, Integer> tbl, IHeap<Integer> heap) throws NotKeyException, DivisionByZeroException, VarNotDefinedException, HeapOutOfRangeException {
        switch (op) {
            case "<":
                if (e1.eval(tbl, heap) < e2.eval(tbl, heap)) {
                    return 1;
                }
                return 0;
            case "<=":
                if (e1.eval(tbl, heap) <= e2.eval(tbl, heap)) {
                    return 1;
                }
                return 0;
            case ">=":
                if (e1.eval(tbl, heap) >= e2.eval(tbl, heap)) {
                    return 1;
                }
                return 0;
            case ">":
                if (e1.eval(tbl, heap) > e2.eval(tbl, heap)) {
                    return 1;
                }
                return 0;
            case "==":
                if (e1.eval(tbl, heap) == e2.eval(tbl, heap)) {
                    return 1;
                }
                return 0;
            case "!=":
                if (e1.eval(tbl, heap) != e2.eval(tbl, heap)) {
                    return 1;
                }
                return 0;
            default:
                return eval(tbl, heap);
        }
    }

    @Override
    public String toString() {
        switch (op) {
            case "<":
                return "(" + e1.toString() + " < " + e2.toString() + ")";
            case "<=":
                return "(" + e1.toString() + " <= " + e2.toString() + ")";
            case ">=":
                return "(" + e1.toString() + " >= " + e2.toString() + ")";
            case ">":
                return "(" + e1.toString() + " > " + e2.toString() + ")";
            case "==":
                return "(" + e1.toString() + " == " + e2.toString() + ")";
            case "!=":
                return "(" + e1.toString() + " != " + e2.toString() + ")";
            default:
                return toString();
        }
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
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
}
