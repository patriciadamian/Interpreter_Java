package Domain.DataStructures.Stack;

import Domain.Stmt.IStmt;

/**
 * Created by Patri on 10/6/2015.
 */
public class ArrayStack implements IStack<IStmt> {

    private IStmt elems[];
    private int nrElemns;

    public ArrayStack(){
        nrElemns = 0;
        elems = new IStmt[10];

    }


    @Override
    public void push(IStmt e) {
        if (nrElemns == elems.length){
            resize();
        }
        elems[nrElemns++] = e;
    }

    private void resize(){
        IStmt[] temp = new IStmt[elems.length*2];
        for (int i = 0;  i < elems.length; i++)
            temp[i] = elems[i];
        elems = temp;
    }

    @Override
    public IStmt pop() throws EmptyStackException{
        if (nrElemns > 0)
            return elems [--nrElemns];
        return null;
    }

    @Override
    public boolean isEmpty() {
        return nrElemns == 0;
    }

//    @Override
//    public IStmt peek() {
//        if (nrElemns == 0)
//            return null;
//        return elems[nrElemns - 1];
//    }

    public String toStr(){
        String res = "";
        for (int i = 0; i < nrElemns ; i++){
            res = elems[i].toString() + " ";
        }
        return res;
    }


}
