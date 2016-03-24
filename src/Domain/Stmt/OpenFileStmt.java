package Domain.Stmt;

import Domain.DataStructures.Dictionary.IsFullDictException;
import Domain.DataStructures.Dictionary.NotKeyException;
import Domain.DataStructures.Heap.HeapOutOfRangeException;
import Domain.DataStructures.List.IsFullListException;
import Domain.Expressions.DivisionByZeroException;
import Domain.Expressions.VarNotDefinedException;
import Domain.MyBuffer;
import Domain.ProgramState.PrgState;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/**
 * Created by Patri on 1/21/2016.
 */
public class OpenFileStmt implements IStmt {
    private String filename;

    public OpenFileStmt(String f){
        this.filename = f;
    }

    @Override
    public PrgState eval(PrgState state) throws NotKeyException, DivisionByZeroException, VarNotDefinedException, IsFullDictException, IsFullListException, HeapOutOfRangeException {
        if(state.getFileTable().isKey(filename)){
            MyBuffer b = state.getFileTable().getValue(filename);
            if (b.getThreadId() == null) {
                state.getFileTable().add(filename, new MyBuffer(state.getId()));
            }
            else{ if(state.getFileTable().getValue(filename).getThreadId() == state.getId()){
                return null;
            }
            else{
                state.getExeStack().push(this);
            }
            }
        }
        else{
            try {
                new RandomAccessFile(filename, "rw");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            state.getFileTable().add(filename, new MyBuffer(state.getId()));
        }
        return null;
    }

    @Override
    public String toString() {
        return "openFile(" + filename + ")";
    }
}
