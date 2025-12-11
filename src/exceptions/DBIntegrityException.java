package src.exceptions;

public class DBIntegrityException extends Exception{
    public DBIntegrityException(String msg){
        super(msg);
    }
}
