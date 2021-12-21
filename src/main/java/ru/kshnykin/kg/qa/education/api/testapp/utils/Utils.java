package ru.kshnykin.kg.qa.education.api.testapp.utils;

import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.function.ThrowingSupplier;

public class Utils {

    public static void execute(Executable goal) {
        try {
            goal.execute();
        } catch (Throwable throwable) {
            if (throwable instanceof RuntimeException) {
                throw (RuntimeException) throwable;
            }
            if (throwable instanceof AssertionError) {
                throw (AssertionError) throwable;
            }
            throw new RuntimeException(throwable);
        }
    }

    public static <T> T supply(ThrowingSupplier<T> goal) {
        try {
            return goal.get();
        } catch (Throwable throwable) {
            if (throwable instanceof RuntimeException) {
                throw (RuntimeException) throwable;
            }
            if (throwable instanceof AssertionError) {
                throw (AssertionError) throwable;
            }
            throw new RuntimeException(throwable);
        }
    }

}
