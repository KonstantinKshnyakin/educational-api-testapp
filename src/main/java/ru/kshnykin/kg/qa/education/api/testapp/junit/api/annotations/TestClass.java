package ru.kshnykin.kg.qa.education.api.testapp.junit.api.annotations;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.kshnykin.kg.qa.education.api.testapp.junit.api.TestNameGenerator;
import ru.kshnykin.kg.qa.education.api.testapp.junit.api.extension.DisabledCondition;

import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@DisplayNameGeneration(TestNameGenerator.class)
@ExtendWith(DisabledCondition.class)
public @interface TestClass {

    String title();

    boolean isDisabled() default false;

    String reason() default "";

}