package ru.kshnykin.kg.qa.education.api.testapp.junit.api;

import org.junit.jupiter.api.DisplayNameGenerator;
import ru.kshnykin.kg.qa.education.api.testapp.junit.api.annotations.Test;
import ru.kshnykin.kg.qa.education.api.testapp.junit.api.annotations.TestClass;

import java.lang.reflect.Method;

public class TestNameGenerator implements DisplayNameGenerator {

    @Override
    public String generateDisplayNameForClass(Class<?> testClass) {
        if (testClass.isAnnotationPresent(TestClass.class)) {
            return testClass.getSimpleName() + ": " + testClass.getAnnotation(TestClass.class).title();
        }
        return testClass.getSimpleName();
    }

    @Override
    public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
        if (nestedClass.isAnnotationPresent(TestClass.class)) {
            return nestedClass.getSimpleName() + ": " + nestedClass.getAnnotation(TestClass.class).title();
        }
        return nestedClass.getSimpleName();
    }

    @Override
    public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
        if (testMethod.isAnnotationPresent(Test.class)) {
            return testMethod.getAnnotation(Test.class).title();
        }
        return testMethod.getName();
    }

}