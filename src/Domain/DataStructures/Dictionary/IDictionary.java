package Domain.DataStructures.Dictionary;
import java.util.*;
import java.util.ArrayList;

import java.io.Serializable;
/**
 * Created by Patri on 11/9/2015.
 */
public interface IDictionary<K,V> extends Serializable {
    boolean isEmpty();
    void add(K key, V value) throws IsFullDictException;
    V getValue(K key) throws NotKeyException ;
    boolean isKey(K key);
    int size();
    void clear();
    String toString();
    void modify(K key, V val);
    ArrayList<K> keys();
    Set<K> keySet();


}
