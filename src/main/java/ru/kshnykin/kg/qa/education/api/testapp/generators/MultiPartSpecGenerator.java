package ru.kshnykin.kg.qa.education.api.testapp.generators;

import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.specification.MultiPartSpecification;
import lombok.experimental.UtilityClass;
import ru.kshnykin.kg.qa.education.api.testapp.utils.IOHelper;

import java.io.File;

@UtilityClass
public class MultiPartSpecGenerator {

    public static MultiPartSpecification gen(File file) {
        return new MultiPartSpecBuilder(file)
                .fileName(file.getName())
                .mimeType(IOHelper.getFileContentType(file))
                .build();
    }

}
