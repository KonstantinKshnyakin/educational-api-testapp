package ru.kshnykin.kg.qa.education.api.testapp.exception;

public class UnimplementedTest extends RuntimeException {

    public UnimplementedTest() {
        super("Unimplemented test");
    }

    public UnimplementedTest(String message) {
        super(message);
    }

}