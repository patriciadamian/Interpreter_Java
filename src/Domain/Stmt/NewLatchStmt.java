package Domain.Stmt;

import Domain.DataStructures.Dictionary.IsFullDictException;
import Domain.DataStructures.Dictionary.NotKeyException;
import Domain.DataStructures.Heap.HeapOutOfRangeException;
import Domain.DataStructures.List.IsFullListException;
import Domain.Expressions.DivisionByZeroException;
import Domain.Expressions.Expr;
import Domain.Expressions.VarNotDefinedException;
import Domain.ProgramState.PrgState;

/**
 * Created by Patri on 1/22/2016.
 */
public class NewLatchStmt implements IStmt {
    private String varname;
    private Integer nr;

    public NewLatchStmt(String  v, Integer nr){
        this.varname = v;
        this.nr = nr;
    }

    public String toString(){
        return "newLatch( " + varname + ", " + nr.toString() + ")";
    }




    @Override
    public PrgState eval(PrgState state) throws NotKeyException, DivisionByZeroException, VarNotDefinedException, IsFullDictException, IsFullListException, HeapOutOfRangeException {


        synchronized (state.getLatchTable()) {
            if (state.getSymTable().isKey(varname)) {
                state.getSymTable().add(varname, state.getLatchTable().add(nr));
            } else {
                state.getSymTable().add(varname, state.getLatchTable().add(nr));
            }
        }
        return null;
    }

    public String getVarname() {
        return varname;
    }

    public void setVarname(String varname) {
        this.varname = varname;
    }

    public Integer getNr() {
        return nr;
    }

    public void setNr(Integer nr) {
        this.nr = nr;
    }
}
