package com.example;

public class IncorrectListException extends RuntimeException {
    IncorrectListException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    IncorrectListException() {
        super();
    }
}
