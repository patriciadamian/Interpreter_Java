package Domain.DataStructures.Dictionary;

import Domain.DataStructures.Dictionary.IDictionary;

import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;

/**
 * Created by Patri on 11/9/2015.
 */
public class MyLibMap<K, V> implements IDictionary<K, V> {
    private HashMap<K, V> hashMap;



    public MyLibMap(){
        hashMap = new HashMap<>();
    }


    public MyLibMap(MyLibMap<String, Integer> e){
        hashMap = (HashMap<K, V>) e.hashMap.clone();
    }

    @Override
    public boolean isEmpty() {
        return hashMap.isEmpty();
    }

    @Override
    public void add(K key, V value) throws IsFullDictException{
        /*
        the initial size of a hash map is 16
         */
        if(this.hashMap.size() == 16) throw new IsFullDictException("Full dictionary!");
        hashMap.put(key, value);

    }

    @Override
    public V getValue(K key) throws NotKeyException {
        if (!this.isKey(key)) throw new NotKeyException("Is not key!");
        return hashMap.get(key);
    }

    @Override
    public boolean isKey(K key) {
        return hashMap.containsKey(key);
    }

    @Override
    public int size() {
        return hashMap.size();
    }


    @Override
    public void clear() {
        hashMap = new HashMap<>();
    }

    @Override
    public void modify(K key, V val) {
        hashMap.put(key, val);

    }

    public ArrayList<K> keys() {
        ArrayList<K> arList = new ArrayList<K>();
        for(K key : hashMap.keySet()){
            arList.add(arList.size(), key);
        }
        return arList;
    }

    public String toString(){
        return " " + hashMap + " ";
    }

    @Override
    public Set<K> keySet() {
        return hashMap.keySet();
    }

    public HashMap<K, V> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<K, V> hashMap) {
        this.hashMap = hashMap;
    }
}
