package Domain.DataStructures.Stack;

import Domain.DomainException;

/**
 * Created by Patri on 11/10/2015.
 */
public class EmptyStackException extends DomainException {
    public  EmptyStackException(String msg){
        super(msg);
    }
}
