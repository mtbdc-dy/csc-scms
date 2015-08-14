package gov.gwssi.csc.scms.service.abnormal;

/**
 * Created by Wang Rui on 2015/6/16.
 */
public class NoSuchAbnormalException extends Exception{
    public NoSuchAbnormalException(){
    }

    public NoSuchAbnormalException(String message){
        super(message);
    }

    public NoSuchAbnormalException(String message, Throwable cause){
        super(message, cause);
    }

    public NoSuchAbnormalException(Throwable cause){
        super(cause);
    }
}
