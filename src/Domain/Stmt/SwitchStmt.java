package Domain.Stmt;

import Domain.DataStructures.Dictionary.IDictionary;
import Domain.DataStructures.Dictionary.IsFullDictException;
import Domain.DataStructures.Dictionary.NotKeyException;
import Domain.DataStructures.Heap.HeapOutOfRangeException;
import Domain.DataStructures.List.IsFullListException;
import Domain.Expressions.*;
import Domain.ProgramState.PrgState;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Patri on 11/9/2015.
 */
public class SwitchStmt implements IStmt {
    private String var;
    private IDictionary<Expr, IStmt> caseTbl;
    private IStmt defaultStmt;


    public SwitchStmt(String v,IDictionary<Expr, IStmt> ct, IStmt stmt){
        this.var = v;
        this.caseTbl = ct;
        this.defaultStmt = stmt;
    }

    @Override
    public PrgState eval(PrgState state) throws NotKeyException, DivisionByZeroException, VarNotDefinedException, IsFullDictException, IsFullListException, HeapOutOfRangeException {

        IDictionary<String, Integer> symTbl = state.getSymTable();

        ArrayList<Expr> list = getCaseTbl().keys();
        Collections.reverse(list);

        IStmt prevIfStmt = getDefaultStmt();

        for (Expr e : list) {
            try {
                prevIfStmt = new IfStmt(new ArithExpr(new ConstExpr(symTbl.getValue(getVar())), e, "-"), prevIfStmt, getCaseTbl().getValue(e));
            } catch (Exception ex) {
                System.out.println("Switch: Input another value!");
            }
        }

        state.getExeStack().push(prevIfStmt);
        return state;
    }

    //    @Override
//    public void eval(IDictionary d) {
//
//    }

    public String toString(){
        String res = "switch( " + var.toString() + " )";
        try{
            for(Expr e : caseTbl.keys()){
                res = res + "case " + e.toString() + " : " + caseTbl.getValue(e).toString();
            }
        }catch (Exception e){
            System.out.println("Please input another value!");
        }
        res = res + "default: " + defaultStmt.toString();
        return res;
    }

    public String getVar() {
        return var;
    }

    public IDictionary<Expr, IStmt> getCaseTbl() {
        return caseTbl;
    }

    public IStmt getDefaultStmt() {
        return defaultStmt;
    }
}
