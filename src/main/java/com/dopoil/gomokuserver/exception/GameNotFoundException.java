package com.dopoil.gomokuserver.exception;

/**
 * Type:  Game not found exception.
 */
public class GameNotFoundException extends Exception{

    private String message;

    /**
     * Instantiates a new Game not found exception.
     *
     * @param message the message
     */
    public GameNotFoundException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
