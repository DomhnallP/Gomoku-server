package com.dopoil.gomokuserver.exception;

public class GameNotJoinableException extends Exception{

    private String message;

    public GameNotJoinableException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
