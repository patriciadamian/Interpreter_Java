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
public class IncStmt implements IStmt {
    private Expr exp;
    private IDictionary symTbl;

    public IncStmt(Expr e){
        this.exp = e;
    }

    public String toString(){
        return "inc( " + exp.toString() + " )";
    }

    @Override
    public PrgState eval(PrgState state) throws NotKeyException, DivisionByZeroException, VarNotDefinedException, IsFullDictException, IsFullListException, HeapOutOfRangeException {
        IDictionary<String, Integer> dict = state.getSymTable();
        if(dict.isKey(this.exp.toString())){
            symTbl.add(exp.toString(), exp.eval(symTbl, state.getHeapTable()) + 1);
        }

        return state;

    }

    public Expr getExp() {
        return exp;
    }

    public void setExp(Expr exp) {
        this.exp = exp;
    }
}
