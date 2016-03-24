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
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Patri on 1/21/2016.
 */
public class CloseFileStmt implements IStmt {
    private String filename;

    public CloseFileStmt(String f){
        this.filename = f;
    }
    @Override
    public PrgState eval(PrgState state) throws NotKeyException, DivisionByZeroException, VarNotDefinedException, IsFullDictException, IsFullListException, HeapOutOfRangeException {
        if (state.getFileTable().isKey(filename)) {
            MyBuffer buf = state.getFileTable().getValue(filename);
            if (buf.getThreadId() == state.getId()) {
                writeToFile(buf.getElements());
                state.getFileTable().add(filename, new MyBuffer(null));
            } else {
                throw new NotKeyException("no suck key");
            }
        }
        else {
            throw new NotKeyException("No suck key");
        }
        return null;
    }

    @Override
    public String toString() {
        return "closeFile(" + filename + ")";
    }

    public void writeToFile(Integer[] buffer){
        try{
            FileChannel fc = new RandomAccessFile(filename, "rw").getChannel();
            fc.position(fc.size());
            if(buffer[0] != null) {
                fc.write(ByteBuffer.wrap(buffer[0].toString().getBytes()));
            }
            if(buffer[1] != null) {
                fc.write(ByteBuffer.wrap(buffer[1].toString().getBytes()));
            }
            fc.close();
        } catch (FileNotFoundException e) {
            System.out.println("No file found");
        } catch (IOException e) {
            System.out.println("Cannot close file");
        }
    }
}
