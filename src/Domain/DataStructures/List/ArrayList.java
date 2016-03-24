package Domain.DataStructures.List;

/**
 * Created by Patri on 10/11/2015.
 */
public class ArrayList implements IList<String> {
    private String[] list;
    private int nrElems;

    public ArrayList(){
        nrElems = 0;
        list = new String[20];
    }


    @Override
    public void add(String e) throws IsFullListException{
        if (nrElems == list.length)
            resize();
        list[nrElems] = e;
        nrElems++;

    }

    @Override
    public boolean isEmpty() {
        return nrElems == 0;
    }

    @Override
    public int size() {
        return nrElems;
    }


    @Override
    public boolean contains(String e) {
        return false;
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0 ; i< nrElems ; i++) res = res + " " + list[i];
        return res;
    }

    public void resize(){
        String[] temp = new String[list.length*2];

        for (int i = 0; i < nrElems; i++){
            temp[i] = list[i];
        }
        list = temp;
    }

    public String getElemAtPos(int i){
        if (i < nrElems)
            return list[i];
        return null;
    }


}
