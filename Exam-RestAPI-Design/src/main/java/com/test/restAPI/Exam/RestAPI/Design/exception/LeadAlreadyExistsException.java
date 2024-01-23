package com.test.restAPI.Exam.RestAPI.Design.exception;

public class LeadAlreadyExistsException extends RuntimeException {

    public LeadAlreadyExistsException(String message) {
        super(message);
    }
}
