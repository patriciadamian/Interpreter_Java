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
 * Created by Patri on 10/11/2015.
 */
public class IfStmt implements IStmt {
    private Expr exp;
    private IStmt thenS;
    private IStmt elseS;

    public IfStmt(Expr e, IStmt t, IStmt el){
        exp = e;
        thenS = t;
        elseS = el;
    }

    @Override
    public String toString() {
        return "IF( " + exp.toString() + " ) THEN ( " + thenS.toString() + " ) ELSE ( " + elseS.toString() + " ) ";

    }

    public Expr getExp() {
        return exp;
    }

    public void setExp(Expr exp) {
        this.exp = exp;
    }

    @Override
    public PrgState eval(PrgState state) throws NotKeyException, DivisionByZeroException, VarNotDefinedException, IsFullDictException, IsFullListException, HeapOutOfRangeException {
        IDictionary<String, Integer> symTbl = state.getSymTable();
        IHeap<Integer> heap = state.getHeapTable();


        if (getExp().eval(symTbl, heap) != 0) {
            state.getExeStack().push(getThenS());
        } else {
            state.getExeStack().push(getElseS());
        }
        return state;
    }

    //    @Override
//    public void eval(IDictionary symTb)throws NotKeyException, DivisionByZeroException, VarNotDefinedException, IsFullDictException, IsFullListException {
//        if (exp.eval(symTb) != 0){
//            thenS.eval(symTb);
//        }else{
//            elseS.eval(symTb);
//        }
//
//    }

    public IStmt getThenS() {
        return thenS;
    }

    public void setThenS(IStmt thenS) {
        this.thenS = thenS;
    }

    public IStmt getElseS() {
        return elseS;
    }

    public void setElseS(IStmt elseS) {
        this.elseS = elseS;
    }
}
