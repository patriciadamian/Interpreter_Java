package Domain.Stmt;

import Domain.DataStructures.Dictionary.IDictionary;
import Domain.DataStructures.Dictionary.IsFullDictException;
import Domain.DataStructures.Dictionary.NotKeyException;
import Domain.DataStructures.Heap.HeapOutOfRangeException;
import Domain.DataStructures.Heap.IHeap;
import Domain.DataStructures.List.IsFullListException;
import Domain.Expressions.DivisionByZeroException;
import Domain.Expressions.Expr;
import Domain.Expressions.VarNotDefinedException;
import Domain.ProgramState.PrgState;

import java.io.Serializable;

/**
 * Created by Patri on 12/7/2015.
 */
public class WriteHeapStmt implements IStmt {
    private String id;
    private Expr expr;


    public WriteHeapStmt(String id, Expr expr){
        this.id = id;
        this.expr = expr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }

    @Override
    public PrgState eval(PrgState state) throws NotKeyException, DivisionByZeroException, VarNotDefinedException, IsFullDictException, IsFullListException, HeapOutOfRangeException {
        IDictionary<String, Integer> symTbl = state.getSymTable();
        IHeap<Integer> heap = state.getHeapTable();

        heap.update(symTbl.getValue(id),getExpr().eval(symTbl,heap));
        return null;

    }

    public String toString(){
        return "writeHeap( " + id + ", " + expr.toString() + " ) ";

    }
}
