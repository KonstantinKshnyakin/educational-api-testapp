package ru.kshnykin.kg.qa.education.api.testapp.junit.api.annotations.tags;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static ru.kshnykin.kg.qa.education.api.testapp.junit.api.annotations.tags.Tags.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Tags({@Tag(ALL), @Tag(PET), @Tag(PET_UPLOAD_IMAGE)})
public @interface PetUploadImageTag {
}