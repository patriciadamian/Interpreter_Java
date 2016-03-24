package Repository;

import Domain.DataStructures.Dictionary.NotKeyException;
import Domain.DataStructures.List.IndexOutOfRangeException;
import Domain.ProgramState.PrgState;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.List;

/**
 * Created by Patri on 10/14/2015.
 */
public class Repository implements IRepository {

    private List<PrgState> states;


    public Repository(List<PrgState> st) {
        this.states = st;
        try {
            Files.deleteIfExists(FileSystems.getDefault().getPath("write.txt"));
        } catch (IOException e) {
            System.out.println("No old write.txt file");
        }
    }

    public Repository() {

        this.states = null;
    }

    @Override
    public List<PrgState> getPrgList() {
        return states;
    }

    @Override
    public void setPrgList(List<PrgState> programs) {
        this.states = programs;

    }


    @Override
    public void serializePrg() {

        ObjectOutputStream out = null;
        try {
            FileOutputStream file = new FileOutputStream("PrgStateFile.ser");
            out = new ObjectOutputStream(file);
            out.writeObject(states);
        } catch (IOException e) {
            System.err.println("Serialization failed: " + e.getMessage());
        } finally {
            if (out != null)
                try {
                    out.close();
                } catch (IOException e) {
                    System.err.println("Error " + e.getMessage());
                }
        }

    }


    @Override
    public void writeToFile() throws IOException, IndexOutOfRangeException, RepoException, NotKeyException {

        try {
            FileChannel fc = new RandomAccessFile("write.txt", "rw").getChannel();
            fc.position(fc.size());
            fc.write(ByteBuffer.wrap(states.toString().getBytes()));
        } catch (IOException e) {
            System.out.println("no such file");
        }


    }


    @Override
    public List<PrgState> deserializePrg() throws IOException {

        ObjectInputStream in = null;
        try {
            FileInputStream file = new FileInputStream("PrgStateFile.ser");
            in = new ObjectInputStream(file);
            //this.states = (List<PrgState>) in.readObject();
            List<PrgState> st = (List<PrgState>) in.readObject();
            this.setPrgList(st);

        } catch (ClassNotFoundException e) {
            System.err.println("Serialization failed: " + e.getMessage());
        } finally {
                in.close();
                return this.states;

        }

    }

}











