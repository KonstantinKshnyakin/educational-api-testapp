package ru.kshnykin.kg.qa.education.api.testapp.exception;

public class UnimplementedTestException extends RuntimeException {

    public UnimplementedTestException() {
        super("Unimplemented test");
    }

    public UnimplementedTestException(String message) {
        super(message);
    }

}