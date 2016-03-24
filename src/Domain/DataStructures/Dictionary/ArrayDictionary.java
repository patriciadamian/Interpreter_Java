package Domain.DataStructures.Dictionary;

import java.util.*;
import java.util.ArrayList;

/**
 * Created by Patri on 10/11/2015.
 */
public class ArrayDictionary implements IDictionary<String, Integer> {
    private int[] values;
    private String[] keys;
    private int nrElems;

    public ArrayDictionary(){
        nrElems = 0;
        keys = new String[20];
        values = new int[20];
    }

    @Override
    public boolean isEmpty() {
        if (nrElems == 0)
            return true;
        return false;
    }

    @Override
    public void add(String key, Integer value) throws IsFullDictException  {
        if (nrElems == values.length)
            resize();
        for (int i = 0; i < nrElems ; i++){
            if (keys[i].equals(key)){
                values[i] = value;
                return;
            }
        }
        values[nrElems] = value;
        keys[nrElems] = key;
        nrElems++;


    }


    @Override
    public Integer getValue(String key) throws NotKeyException {
        for (int i = 0; i < nrElems; i++ ){
            if (keys[i].equals(key)){
                return this.values[i];
            }
        }
        throw new NotKeyException("");
    }



    @Override
    public boolean isKey(String key) {
        for (int i = 0; i < nrElems; i++ ){
            if (keys[i].equals(key))
                return true;
        }
        return false;
    }

    @Override
    public int size() {
        return nrElems;
    }


    @Override
    public void clear() {

    }

    @Override
    public void modify(String key, Integer val) {
        for (int i = 0; i < nrElems; i++){
            if (keys[i].equals((key))){
                values[i] = val;
                break;
            }
        }

    }

    @Override
    public ArrayList<String> keys() {
        return null;
    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < nrElems ; i++){
            res = res + keys[i] + " : " + values[i] + " | ";
        }
        return res;
    }

    public void resize() {
        int[] tempV = new int[values.length*2];
        String[] tempK = new String[values.length*2];

        for(int i = 0; i < nrElems; i++){
            tempV[i] = values[i];
            tempK[i] = keys[i];
        }

        values = tempV;
        keys = tempK;

   }



}
