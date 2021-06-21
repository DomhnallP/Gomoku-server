package com.dopoil.gomokuserver.exception;

/**
 * Type: Invalid move exception.
 */
public class InvalidMoveException extends Exception {
    private String message;

    /**
     * Instantiates a new Invalid move exception.
     *
     * @param message the message
     */
    public InvalidMoveException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
