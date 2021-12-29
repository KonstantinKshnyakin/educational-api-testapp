package ru.kshnykin.kg.qa.education.api.testapp.junit.api.extension;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;
import ru.kshnykin.kg.qa.education.api.testapp.junit.api.annotations.Test;
import ru.kshnykin.kg.qa.education.api.testapp.junit.api.annotations.TestClass;

import java.lang.reflect.AnnotatedElement;
import java.util.Optional;

public class DisabledCondition implements ExecutionCondition {

    private static final ConditionEvaluationResult ENABLED = ConditionEvaluationResult.enabled(
            "В аннотации @Test isDisabled=false");

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
        Optional<AnnotatedElement> element = context.getElement();
        if (element.isPresent()) {
            AnnotatedElement annElement = element.get();
            if (annElement.isAnnotationPresent(Test.class)) {
                Test test = annElement.getAnnotation(Test.class);
                if (test.isDisabled()) {
                    return ConditionEvaluationResult.disabled(test.reason().isBlank() ? annElement + " is disabled" : test.reason());
                }
            }
            if (annElement.isAnnotationPresent(TestClass.class)) {
                TestClass test = annElement.getAnnotation(TestClass.class);
                if (test.isDisabled()) {
                    return ConditionEvaluationResult.disabled(test.reason().isBlank() ? annElement + " is disabled" : test.reason());
                }
            }
        }
        return ENABLED;
    }

}