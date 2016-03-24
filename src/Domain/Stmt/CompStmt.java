package Domain.Stmt;

import Domain.DataStructures.Dictionary.IDictionary;
import Domain.DataStructures.Dictionary.IsFullDictException;
import Domain.DataStructures.Dictionary.NotKeyException;
import Domain.DataStructures.Heap.HeapOutOfRangeException;
import Domain.DataStructures.List.IsFullListException;
import Domain.Expressions.DivisionByZeroException;
import Domain.Expressions.VarNotDefinedException;
import Domain.ProgramState.PrgState;

/**
 * Created by Patri on 10/11/2015.
 */
public class CompStmt implements IStmt {
    private IStmt first;
    private IStmt snd;


    public CompStmt(IStmt f, IStmt s){
        first = f;
        snd = s;

    }

    @Override
    public String toString() {
        return  first.toString() + " ; " + snd.toString();  //a = 2; print a => (a = 2; print a)

    }

    @Override
    public PrgState eval(PrgState state) throws NotKeyException, DivisionByZeroException, VarNotDefinedException, IsFullDictException, IsFullListException, HeapOutOfRangeException {
        state.getExeStack().push(snd);
        state.getExeStack().push(first);
        return state;
    }

    public IStmt getFirst() {
        return first;
    }

    public void setFirst(IStmt first) {
        this.first = first;
    }

//    @Override
//    public void eval(IDictionary symTb) throws NotKeyException, DivisionByZeroException, VarNotDefinedException, IsFullDictException, IsFullListException {
//        first.eval(symTb);
//        snd.eval(symTb);
//
//    }

    public IStmt getSnd() {
        return snd;
    }

    public void setSnd(IStmt snd) {
        this.snd = snd;
    }
}
