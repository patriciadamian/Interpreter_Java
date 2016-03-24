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
public class AwaitStmt implements IStmt {
    private String varname;

    public AwaitStmt(String v){
        this.varname = v;
    }


    @Override
    public PrgState eval(PrgState state) throws NotKeyException, DivisionByZeroException, VarNotDefinedException, IsFullDictException, IsFullListException, HeapOutOfRangeException {
        if(state.getSymTable().isKey(varname)){
            Integer val = state.getLatchTable().get(state.getSymTable().getValue(varname));
            if(val != 0){
                state.getExeStack().push(this);
            }
        }
        else{
            throw new NotKeyException("no such key");
        }
        return state;
    }

    @Override
    public String toString() {
        return "await(" + varname + ")";
    }
}



