package Domain.Expressions;

import Domain.DomainException;

/**
 * Created by Patri on 11/10/2015.
 */
public class VarNotDefinedException extends DomainException {
    public VarNotDefinedException(String msg){
        super(msg);
    }
}
