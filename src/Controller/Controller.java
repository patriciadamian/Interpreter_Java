package Controller;


import Domain.DataStructures.Dictionary.IDictionary;
import Domain.DataStructures.Dictionary.IsFullDictException;
import Domain.DataStructures.Dictionary.NotKeyException;
import Domain.DataStructures.Heap.HeapOutOfRangeException;
import Domain.DataStructures.List.IList;
import Domain.DataStructures.List.IndexOutOfRangeException;
import Domain.DataStructures.List.IsFullListException;
import Domain.DataStructures.Stack.*;
import Domain.Expressions.*;
import Domain.ProgramState.PrgState;
import Domain.Stmt.*;
import Repository.IRepository;

import java.io.IOException;
import java.util.ArrayList;
import Domain.DataStructures.Stack.EmptyStackException;
import Repository.RepoException;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by Patri on 10/26/2015.
 */
public class Controller {
    private IRepository repo;
    private boolean debugFlag = false;
    //private PrgState crntPrg;
    private boolean logFlag = false;



    public Controller(IRepository r) throws IndexOutOfRangeException, RepoException {
        this.repo = r;
        //crntPrg = repo.getCrtPrg();

    }

    public void changeDebug(){
        debugFlag = !debugFlag;
    }

    public void changeLogFlag() {
        logFlag = !logFlag;
    }

    public boolean isLogFlag(){return logFlag;}


    public void serializeProgram(){
        repo.serializePrg();
    }

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(PrgState::isNotComplete)
                .collect(Collectors.toList());
    }

    private void oneStepForAllProgr(List<PrgState> prgList) throws InterruptedException, IndexOutOfRangeException, NotKeyException, RepoException, IOException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        List<Callable<PrgState>> callList = prgList.stream()
                .map(p -> (Callable<PrgState>) p::oneStep)
                .collect(Collectors.toList());

        List<PrgState> newPrgList = exec.invokeAll(callList)
                .stream()
                .map(future -> { try {return future.get();} catch(Exception e) { return null; }})
                .filter(p -> p != null)
                .collect(Collectors.toList());

        prgList.forEach(p -> {if(!newPrgList.stream().anyMatch(s -> s.getId() == p.getId())) newPrgList.add(p);});

        if (logFlag) {
            repo.writeToFile();
        }
        repo.setPrgList(newPrgList);

    }

    public void oneStep() throws InterruptedException, NotKeyException, IOException, RepoException, IndexOutOfRangeException {
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
        oneStepForAllProgr(prgList);
    }




    public void allStep() throws DivisionByZeroException, VarNotDefinedException, NotKeyException, IsFullListException, IsFullDictException, IndexOutOfRangeException, RepoException, IOException, HeapOutOfRangeException, EmptyStackException, InterruptedException {

        while (true){
            List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
            if (prgList.size() == 0){
                return;
            }else {
                oneStepForAllProgr(prgList);
            }
        }

    }



    public List<PrgState> getPrgList(){
        return repo.getPrgList();
    }

    public IRepository getRepo() {
        return repo;
    }

    public void setRepo(IRepository repo) {
        this.repo = repo;
    }

    public boolean isDebugFlag() {
        return debugFlag;
    }

    public void setDebugFlag(boolean debugFlag) {
        this.debugFlag = debugFlag;
    }


}
