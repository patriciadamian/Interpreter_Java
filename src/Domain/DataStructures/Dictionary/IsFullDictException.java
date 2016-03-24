package Domain.DataStructures.Dictionary;

import Domain.DomainException;

/**
 * Created by Patri on 11/10/2015.
 */
public class IsFullDictException extends DomainException {
    public IsFullDictException(String msg){
        super(msg);
    }
}
