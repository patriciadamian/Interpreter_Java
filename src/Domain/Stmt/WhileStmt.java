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

/**
 * Created by Patri on 11/3/2015.
 */
public class WhileStmt implements IStmt {
    private Expr ex;
    private IStmt stmt;


    public WhileStmt(Expr e, IStmt s){
        ex = e;
        stmt = s;
    }
    @Override
    public String toString() {
        return "WHILE( " + ex.toString() + " ) { " + stmt.toString() + " } ";
    }

//    @Override
//    public void eval(IDictionary d) {
//
//    }


    @Override
    public PrgState eval(PrgState state) throws NotKeyException, DivisionByZeroException, VarNotDefinedException, IsFullDictException, IsFullListException, HeapOutOfRangeException {
        IDictionary<String, Integer> symTbl = state.getSymTable();
        IHeap<Integer> heap = state.getHeapTable();

        if (getEx().eval(symTbl, heap) != 0) {

            state.getExeStack().push(this);
            state.getExeStack().push(this.getStmt());
        }
        return state;
    }

    public Expr getEx() {
        return ex;
    }

    public void setEx(Expr ex) {
        this.ex = ex;
    }

    public IStmt getStmt() {
        return stmt;
    }

    public void setStmt(IStmt stmt) {
        this.stmt = stmt;
    }
}
