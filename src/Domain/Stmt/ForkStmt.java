package Domain.Stmt;

import Domain.DataStructures.Dictionary.IDictionary;
import Domain.DataStructures.Dictionary.IsFullDictException;
import Domain.DataStructures.Dictionary.MyLibMap;
import Domain.DataStructures.Dictionary.NotKeyException;
import Domain.DataStructures.Heap.HeapOutOfRangeException;
import Domain.DataStructures.List.IsFullListException;
import Domain.DataStructures.Stack.MyLibStack;
import Domain.Expressions.DivisionByZeroException;
import Domain.Expressions.VarNotDefinedException;
import Domain.ProgramState.PrgState;

/**
 * Created by Patri on 1/4/2016.
 */
public class ForkStmt implements IStmt{

    private IStmt stmt;

    public ForkStmt(IStmt s){
        this.stmt = s;
    }

    public IStmt getStmt() {
        return stmt;
    }

    public void setStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public PrgState eval(PrgState state) throws NotKeyException, DivisionByZeroException, VarNotDefinedException, IsFullDictException, IsFullListException, HeapOutOfRangeException {
        MyLibStack<IStmt> newStk = new MyLibStack<>();
        IDictionary<String, Integer> cloneSymTbl = new MyLibMap<>((MyLibMap<String, Integer>)state.getSymTable());
        return new PrgState(newStk, cloneSymTbl, state.getOut(), state.getHeapTable(), state.getFileTable(), state.getLatchTable(), stmt);
    }

    public String toString(){
        return "fork( " + stmt.toString() + " )";
    }
}
