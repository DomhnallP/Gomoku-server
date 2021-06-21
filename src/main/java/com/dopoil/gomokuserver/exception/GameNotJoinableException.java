package com.dopoil.gomokuserver.exception;

/**
 * Type: Game not joinable exception.
 */
public class GameNotJoinableException extends Exception{

    private String message;

    /**
     * Instantiates a new Game not joinable exception.
     *
     * @param message the message
     */
    public GameNotJoinableException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
