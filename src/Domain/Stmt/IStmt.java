package Domain.Stmt;

import Domain.DataStructures.Dictionary.IDictionary;
import Domain.DataStructures.Dictionary.IsFullDictException;
import Domain.DataStructures.Dictionary.NotKeyException;
import Domain.DataStructures.Heap.HeapOutOfRangeException;
import Domain.DataStructures.List.IsFullListException;
import Domain.Expressions.DivisionByZeroException;
import Domain.Expressions.VarNotDefinedException;
import Domain.ProgramState.PrgState;

import java.io.Serializable;

/**
 * Created by Patri on 10/11/2015.
 */
public interface IStmt extends Serializable{
    String toString();
    //public void eval(IDictionary d) throws NotKeyException, DivisionByZeroException, VarNotDefinedException, IsFullDictException, IsFullListException;
    PrgState eval(PrgState state) throws NotKeyException, DivisionByZeroException, VarNotDefinedException, IsFullDictException, IsFullListException, HeapOutOfRangeException;

}
