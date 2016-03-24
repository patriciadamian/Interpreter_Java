package Domain.ProgramState;

import Controller.StmtExecutionException;
import Domain.DataStructures.Dictionary.IDictionary;
import Domain.DataStructures.Dictionary.IsFullDictException;
import Domain.DataStructures.Dictionary.MyLibMap;
import Domain.DataStructures.Dictionary.NotKeyException;
import Domain.DataStructures.Heap.HeapOutOfRangeException;
import Domain.DataStructures.Heap.IHeap;
import Domain.DataStructures.Latch.ILatch;
import Domain.DataStructures.List.IList;
import Domain.DataStructures.List.IsFullListException;
import Domain.DataStructures.Stack.EmptyStackException;
import Domain.DataStructures.Stack.IStack;
import Domain.Expressions.DivisionByZeroException;
import Domain.Expressions.VarNotDefinedException;
import Domain.MyBuffer;
import Domain.Stmt.IStmt;

import java.io.Serializable;

/**
 * Created by Patri on 10/14/2015.
 */
public class PrgState implements Serializable{
    private IStack<IStmt> exeStack;
    private IDictionary<String, Integer> symTable;
    private IList<String> out;
    private IHeap<Integer> heapTable;
    private IDictionary<String, MyBuffer> fileTable;
    private ILatch<Integer,Integer> latchTable;
    private IStmt orgProgram;
    private int id;
    private static int increment = 0;

    public PrgState(IStack<IStmt> s, IDictionary<String, Integer> d, IList<String> l, IHeap<Integer> heap, IDictionary<String, MyBuffer> fileTable, ILatch<Integer,Integer> latchTable, IStmt prg){
        this.id = increment++;
        this.exeStack = s;
        this.symTable = d;
        this.out = l;
        this.heapTable = heap;
        this.fileTable = fileTable;
        this.latchTable = latchTable;
        this.orgProgram = prg;
        exeStack.push(orgProgram);
        //exeStack.push(prg);
    }


    public PrgState(IStack<IStmt> s, IDictionary<String, Integer> d, IList<String> l, IHeap<Integer> heap, IDictionary<String, MyBuffer> fileTable, ILatch<Integer, Integer> latchTable, IStmt prg, int i){
        this.id = i;
        this.exeStack = s;
        this.symTable = d;
        this.out = l;
        this.heapTable = heap;
        this.fileTable = fileTable;
        this.latchTable = latchTable;
        this.orgProgram = prg;
        exeStack.push(orgProgram);
    }

    public boolean isNotComplete() { return !exeStack.isEmpty(); }

    public PrgState oneStep() throws EmptyStackException, DivisionByZeroException, HeapOutOfRangeException, IsFullDictException, VarNotDefinedException, NotKeyException, IsFullListException, StmtExecutionException {
        try{
            IStmt crntStmt = exeStack.pop();
            return crntStmt.eval(this);
        }catch (EmptyStackException e){
            throw new StmtExecutionException();
        }
    }

    public String toString(){
        return  "---------------------------------------------------------------------------------------\n" +
                "id: " + id + "\n " +
                "Execution Stack : { " + exeStack.toString() + "  }\n " +
                "Symbol Table : { " + symTable.toString() + " }\n " +
                "Heap : { " + heapTable.toString() + " }\n" +
                "Latch Table: {" + latchTable.toString() + " }\n" +
                "Output: { " + out.toString() + " }\n" +
                "---------------------------------------------------------------------------------------\n" ;

    }


    public IStack<IStmt> getExeStack() {
        return exeStack;
    }

    public IHeap<Integer> getHeapTable() {
        return heapTable;
    }

    public ILatch<Integer, Integer> getLatchTable() {
        return latchTable;
    }

    public IDictionary<String, Integer> getSymTable() {
        return symTable;
    }

    public IList<String> getOut() {
        return out;
    }

    public IStmt getOrgProgram() {
        return orgProgram;
    }

    public int getId() { return id; }

    public IDictionary<String, MyBuffer> getFileTable() {
        return fileTable;
    }
}

