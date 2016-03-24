package Domain.Expressions;

import Domain.DataStructures.Dictionary.IDictionary;
import Domain.DataStructures.Dictionary.NotKeyException;
import Domain.DataStructures.Heap.HeapOutOfRangeException;
import Domain.DataStructures.Heap.IHeap;

/**
 * Created by Patri on 11/9/2015.
 */
public class LogicalExpr extends Expr {
    private String op;
    private Expr e1;
    private Expr e2;


    /*
    * &&
    * ||
    * !
     */


    public LogicalExpr(Expr ex1, Expr ex2, String o){
        this.op = o;
        this.e1 = ex1;
        this.e2 = ex2;

    }

    public LogicalExpr(Expr exp1, String o){
        this.e1 = exp1;
        this.op = o;
    }
    @Override
    public int eval(IDictionary<String, Integer> tbl, IHeap<Integer> heap) throws NotKeyException, DivisionByZeroException, VarNotDefinedException, HeapOutOfRangeException {
        switch (op){
            case "&&":
                if (e1.eval(tbl, heap)!=0 && e2.eval(tbl, heap)!=0) {
                    return 1;
                }
                return 0;
            case "||":
                if (e1.eval(tbl, heap)!=0 || e2.eval(tbl, heap)!=0){
                    return 1;
                }
                return 0;
            case "!":
                if (e1.eval(tbl, heap) == 0){
                    return 1;
                }
                return 0;
            default:
                return eval(tbl, heap);
        }

    }

    @Override
    public String toString() {
        switch (op){
            case "&&": return "( " + e1.toString() + " && " + e2.toString() + " ) ";
            case "||": return "( " + e1.toString() + " || " + e2.toString() + " ) ";
            case "!": return " !( " + e1.toString() + " ) ";
            default: return toString();
        }
    }
}
