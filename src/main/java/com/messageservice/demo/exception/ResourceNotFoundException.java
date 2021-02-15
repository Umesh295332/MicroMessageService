package com.messageservice.demo.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class ResourceNotFoundException extends Exception{

    public ResourceNotFoundException(){
        super();
    }

    public ResourceNotFoundException(String msg){
        super(msg);
    }


}
