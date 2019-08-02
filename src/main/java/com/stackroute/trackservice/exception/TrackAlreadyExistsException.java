package com.stackroute.trackservice.exception;

public class TrackAlreadyExistsException extends Exception {
    private String meassage;

    public TrackAlreadyExistsException() {

    }

    public TrackAlreadyExistsException(String message) {
        super(message);
        this.meassage = message;
    }
}
