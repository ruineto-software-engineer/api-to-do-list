package com.v8.apitodolist.service.exception;

public class EntityBadRequestException extends RuntimeException{

    public EntityBadRequestException(String message){
        super(message);
    }

}
