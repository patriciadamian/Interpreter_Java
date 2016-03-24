package Domain.DataStructures.Dictionary;

import Domain.DomainException;

/**
 * Created by Patri on 11/10/2015.
 */
public class NotKeyException extends DomainException {
    public NotKeyException(String msg){
        super(msg);
    }
}
