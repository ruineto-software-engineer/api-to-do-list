package com.v8.apitodolist.service.exception;

import com.v8.apitodolist.model.Task;

import java.util.function.Consumer;

public class EntityConflictException extends RuntimeException {

    public EntityConflictException(String message){
        super(message);
    }

}
