package gov.gwssi.csc.scms.service.student;

/**
 * Created by Wang Rui on 2015/6/16.
 */
public class NoSuchStudentException extends Exception{
    public NoSuchStudentException(){
    }

    public NoSuchStudentException(String message){
        super(message);
    }

    public NoSuchStudentException(String message, Throwable cause){
        super(message, cause);
    }

    public NoSuchStudentException(Throwable cause){
        super(cause);
    }
}
