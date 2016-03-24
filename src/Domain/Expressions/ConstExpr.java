package Domain.Expressions;

import Domain.DataStructures.Dictionary.IDictionary;
import Domain.DataStructures.Dictionary.NotKeyException;
import Domain.DataStructures.Heap.IHeap;

/**
 * Created by Patri on 10/13/2015.
 */
public class ConstExpr extends Expr {
    private Integer number;


    public ConstExpr(Integer nr){

        this.number = nr;
    }

    @Override
    public int eval(IDictionary<String, Integer> tbl, IHeap<Integer> heap) throws NotKeyException, DivisionByZeroException, VarNotDefinedException {
        return number;
    }

//    @Override
//    public int eval(IDictionary<String, Integer> tbl) {
//        return number;
//    }

    @Override
    public String toString() {
        return " " + String.valueOf(number);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }




}
