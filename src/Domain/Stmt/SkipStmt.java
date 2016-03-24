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
 * Created by Patri on 11/10/2015.
 */
public class SkipStmt implements IStmt {

    @Override
    public PrgState eval(PrgState state) throws NotKeyException, DivisionByZeroException, VarNotDefinedException, IsFullDictException, IsFullListException, HeapOutOfRangeException {
        return state;
    }
    //    @Override
//    public void eval(IDictionary d) {
//
//    }

    public String toString(){
        return "Skip ";

    }
}
