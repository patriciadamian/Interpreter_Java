package Domain.Stmt;

import Domain.DataStructures.Dictionary.IDictionary;
import Domain.DataStructures.Dictionary.IsFullDictException;
import Domain.DataStructures.Dictionary.NotKeyException;
import Domain.DataStructures.Heap.HeapOutOfRangeException;
import Domain.DataStructures.Heap.IHeap;
import Domain.DataStructures.List.IList;
import Domain.DataStructures.List.IsFullListException;
import Domain.Expressions.DivisionByZeroException;
import Domain.Expressions.Expr;
import Domain.Expressions.VarNotDefinedException;
import Domain.ProgramState.PrgState;

/**
 * Created by Patri on 10/11/2015.
 */
public class PrintStmt implements IStmt {
    private Expr exp;
    private IList output;


    public PrintStmt(Expr e){
        exp = e;
    }

    @Override
    public String toString() {
        return "print( " + exp.toString() + " )";
    }

    @Override
    public PrgState eval(PrgState state) throws NotKeyException, DivisionByZeroException, VarNotDefinedException, IsFullDictException, IsFullListException, HeapOutOfRangeException {
        IDictionary<String, Integer> symTbl = state.getSymTable();
        IHeap<Integer> heap = state.getHeapTable();
        IList<String> out = state.getOut();
        out.add(Integer.toString(getExp().eval(symTbl, heap)));
        return state;
    }

    //    @Override
//    public void eval(IDictionary d) throws NotKeyException, DivisionByZeroException, VarNotDefinedException, IsFullDictException, IsFullListException {
//        output.add(exp.toString());
//
//    }



    public Expr getExp() {
        return exp;
    }

    public void setExp(Expr exp) {
        this.exp = exp;
    }


    public IList getOutput() {
        return output;
    }

    public void setOutput(IList output) {
        this.output = output;
    }
}
