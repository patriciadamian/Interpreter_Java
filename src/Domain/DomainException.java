package Domain;

/**
 * Created by Patri on 11/10/2015.
 */
public class DomainException extends Exception {
    public DomainException(){
        super("Exception occurred in DOMAIN! ");
    }

    public DomainException(String msg){
        super(msg);
    }
}
