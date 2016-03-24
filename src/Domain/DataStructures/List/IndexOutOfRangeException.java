package Domain.DataStructures.List;

import Domain.DomainException;

/**
 * Created by Patri on 11/10/2015.
 */
public class IndexOutOfRangeException extends DomainException {
    public IndexOutOfRangeException(String msg){
        super(msg);
    }
}
