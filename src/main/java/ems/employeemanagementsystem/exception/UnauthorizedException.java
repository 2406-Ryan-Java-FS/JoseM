package ems.employeemanagementsystem.exception;

public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException(){
    }

    public UnauthorizedException(String msg){
        super(msg);
    }
}
