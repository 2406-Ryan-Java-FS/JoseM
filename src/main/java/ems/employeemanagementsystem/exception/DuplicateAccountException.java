package ems.employeemanagementsystem.exception;

public class DuplicateAccountException extends RuntimeException{

    public DuplicateAccountException(){

    }
    public DuplicateAccountException(String msg){
        super(msg);
    }
}
