
import Domain.DataStructures.Dictionary.IsFullDictException;
import Domain.DataStructures.Dictionary.NotKeyException;
import Domain.DataStructures.Heap.HeapOutOfRangeException;
import Domain.DataStructures.List.IndexOutOfRangeException;
import Domain.DataStructures.List.IsFullListException;
import Domain.DataStructures.Stack.EmptyStackException;
import Repository.RepoException;
import Ui.*;

/**
 * Created by Patri on 10/6/2015.
 */
public class Main {
    public static void main(String[] args) throws IsFullListException, NotKeyException, WrongDataTypeException, RepoException, IndexOutOfRangeException, IsFullDictException, HeapOutOfRangeException, EmptyStackException, InterruptedException {

        Ui console = new Ui();
        console.run();
    }
}
