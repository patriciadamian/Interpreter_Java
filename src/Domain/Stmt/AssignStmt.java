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
public class AssignStmt implements IStmt {
    private String id;
    private Expr exp;

//    @Override
//    public void eval(IDictionary symT) throws NotKeyException, DivisionByZeroException, VarNotDefinedException, IsFullDictException, IsFullListException {
//        symT.add(id, exp.eval(symT));
//
//    }

    public AssignStmt(String i, Expr e){
        id = i;
        exp = e;
    }


    @Override
    public String toString() {
        return id + " = " + exp.toString();

    }

    @Override
    public PrgState eval(PrgState state) throws NotKeyException, DivisionByZeroException, VarNotDefinedException, IsFullDictException, IsFullListException, HeapOutOfRangeException {

        IDictionary<String, Integer> symTbl = state.getSymTable();
        IHeap<Integer> heap = state.getHeapTable();
        int val = exp.eval(symTbl, heap);
        symTbl.add(id, val);

        return state;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Expr getExp() {
        return exp;
    }

    public void setExp(Expr exp) {
        this.exp = exp;
    }
}
