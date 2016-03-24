package Domain.Stmt;

import Domain.DataStructures.Dictionary.IDictionary;
import Domain.DataStructures.Dictionary.IsFullDictException;
import Domain.DataStructures.Dictionary.NotKeyException;
import Domain.DataStructures.Heap.HeapOutOfRangeException;
import Domain.DataStructures.List.IsFullListException;
import Domain.Expressions.DivisionByZeroException;
import Domain.Expressions.Expr;
import Domain.Expressions.VarNotDefinedException;
import Domain.ProgramState.PrgState;

/**
 * Created by Patri on 1/20/2016.
 */
public class IfSkipStmt implements IStmt {
    private Expr exp;
    private IStmt thenS;
    private IStmt elseS;
    private IDictionary symT;

    public IfSkipStmt(Expr e, IStmt th)
    {
        this.exp = e;
        this.thenS = th;
        this.elseS = null;
    }

    @Override
    public String toString()
    {
        return "IF (" + exp.toString() + ") THEN (" + thenS.toString() + ") ELSE (" + elseS.toString() + ")";
    }

    @Override
    public PrgState eval(PrgState state) throws NotKeyException, DivisionByZeroException, VarNotDefinedException, IsFullDictException, IsFullListException, HeapOutOfRangeException {
        return state;
    }

    public Expr getExp() {
        return exp;
    }

    public void setExp(Expr exp) {
        this.exp = exp;
    }

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

    public IDictionary getSymT() {
        return symT;
    }

    public void setSymT(IDictionary symT) {
        this.symT = symT;
    }
}
