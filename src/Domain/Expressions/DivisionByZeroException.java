package Domain.Expressions;

import Domain.DomainException;

/**
 * Created by Patri on 11/10/2015.
 */
public class DivisionByZeroException extends DomainException {
    public DivisionByZeroException(String msg){
        super(msg);
    }
}
