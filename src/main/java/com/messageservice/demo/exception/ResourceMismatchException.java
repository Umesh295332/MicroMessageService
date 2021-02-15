package com.messageservice.demo.exception;

public class ResourceMismatchException extends Exception {

    public ResourceMismatchException(){
        super();
    }

    public ResourceMismatchException(final String msg){
        super(msg);
    }
}
