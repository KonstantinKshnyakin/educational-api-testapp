package ru.kshnykin.kg.qa.education.api.testapp.junit.api.annotations;

import org.junit.jupiter.api.extension.ExtendWith;
import ru.kshnykin.kg.qa.education.api.testapp.junit.api.extension.DisabledCondition;

import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ExtendWith(DisabledCondition.class)
@org.junit.jupiter.api.Test
public @interface Test {

    String title();

    boolean isDisabled() default false;

    String reason() default "";

}