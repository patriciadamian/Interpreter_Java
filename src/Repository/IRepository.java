package Repository;

import Domain.DataStructures.Dictionary.NotKeyException;
import Domain.DataStructures.List.IList;
import Domain.DataStructures.List.IndexOutOfRangeException;
import Domain.ProgramState.PrgState;

import java.io.IOException;
import java.util.List;

/**
 * Created by Patri on 10/14/2015.
 */
public interface IRepository {

    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> programs);

    void serializePrg();
    void writeToFile() throws IOException, IndexOutOfRangeException, RepoException, NotKeyException;
    List<PrgState> deserializePrg() throws IOException;
}
