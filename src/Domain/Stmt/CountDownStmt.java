package Domain.Stmt;

import Domain.DataStructures.Dictionary.IsFullDictException;
import Domain.DataStructures.Dictionary.NotKeyException;
import Domain.DataStructures.Heap.HeapOutOfRangeException;
import Domain.DataStructures.List.IsFullListException;
import Domain.Expressions.DivisionByZeroException;
import Domain.Expressions.VarNotDefinedException;
import Domain.ProgramState.PrgState;

/**
 * Created by Patri on 1/22/2016.
 */
public class CountDownStmt implements IStmt {
    private String varname;

    public CountDownStmt(String v){
        this.varname = v;
    }

    @Override
    public String toString() {
        return "countDown(" + varname + ")";
    }
    @Override
    public PrgState eval(PrgState state) throws NotKeyException, DivisionByZeroException, VarNotDefinedException, IsFullDictException, IsFullListException, HeapOutOfRangeException {
        Object lock = new Object();
        if(state.getSymTable().isKey(varname)){
            synchronized (lock) {
                Integer val = state.getLatchTable().get(state.getSymTable().getValue(varname)); //throw excp if not index
                if (val > 0) {
                    Integer newVal = val - 1;
                    state.getLatchTable().update(state.getSymTable().getValue(varname), newVal);
                }
            }
        }
        else{
            throw new NotKeyException("no such key");
        }
        return state;
    }
}



