package com.simplicity.Exceptions;

public class WorkCooldownException extends Exception {
    public WorkCooldownException() {
        super("Work is in cooldown.");
    }

    public WorkCooldownException(String message) {
        super(message);
    }

}
