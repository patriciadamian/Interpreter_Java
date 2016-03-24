package Domain.Stmt;

import Domain.DataStructures.Dictionary.IsFullDictException;
import Domain.DataStructures.Dictionary.NotKeyException;
import Domain.DataStructures.Heap.HeapOutOfRangeException;
import Domain.DataStructures.List.IsFullListException;
import Domain.Expressions.DivisionByZeroException;
import Domain.Expressions.Expr;
import Domain.Expressions.VarNotDefinedException;
import Domain.MyBuffer;
import Domain.ProgramState.PrgState;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Patri on 1/21/2016.
 */
public class WriteFileStmt implements IStmt {
    private String filename;
    private Expr varname;

    public WriteFileStmt(String f, Expr v){
        this.filename = f;
        this.varname = v;
    }

    @Override
    public PrgState eval(PrgState state) throws NotKeyException, DivisionByZeroException, VarNotDefinedException, IsFullDictException, IsFullListException, HeapOutOfRangeException {
        System.out.println("what da fac");
        if(state.getFileTable().isKey(filename)) {
            MyBuffer buf = state.getFileTable().getValue(filename);
            if (buf.getThreadId() == state.getId()) {
                Integer[] b = buf.getElements();
                if (b[0] == null) {
                    System.out.println("what da fac 2");
                    b[0] = varname.eval(state.getSymTable(), state.getHeapTable());
                    //state.getFileTable().add(filename, new MyBuffer(b, state.getId()));
                } else if (b[1] == null) {
                    System.out.println("what da fac 3");
                    b[1] = varname.eval(state.getSymTable(), state.getHeapTable());
                    //state.getFileTable().add(filename, new MyBuffer(b, state.getId()));
                } else {
                    System.out.println("pasul 156145");
                    writeToFile(b);
                    System.out.println("pasul 1515154");
                    b[0] = varname.eval(state.getSymTable(), state.getHeapTable());
                    b[1] = null;
                    //state.getFileTable().add(filename, new MyBuffer(b, state.getId()));
                }
            } else {
                System.out.println("what da fac 5");
                state.getExeStack().push(this);
            }
        }
        else{
            //Integer[] elem = new Integer[2];
            //elem[0] = varname.eval(state.getSymTable(), state.getHeap());
            //elem[1] = null;
            //MyBuffer buff = new MyBuffer(elem, state.getId());
            //state.getFileTable().add(filename, buff);
            System.out.println("what da fac 6");
            throw new NotKeyException("no such key");
        }
        System.out.println("fsdfjdsnfjkdkjf");
        return null;
    }

    public void writeToFile(Integer[] b){
        try {
            System.out.println("pasul 1");
            FileChannel fc = new RandomAccessFile(filename, "rw").getChannel();
            System.out.println("pasul 2");
            fc.position(fc.size());
            System.out.println("pasul 3");
            fc.write(ByteBuffer.wrap(b[0].toString().getBytes()));
            System.out.println("pasul 4");
            fc.write(ByteBuffer.wrap(b[1].toString().getBytes()));
            System.out.println("pasul 5");
            //fc.close();
        } catch (FileNotFoundException e) {
            System.out.println("No file found");
        } catch (IOException e) {
            System.out.println("Cannot close file");
        }

    }

    @Override
    public String toString() {
        return "writeFile(" + filename + ", " + varname.toString() + ")";
    }

}
