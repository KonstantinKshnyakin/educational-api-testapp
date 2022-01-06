package ru.kshnykin.kg.qa.education.api.testapp.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import ru.kshnykin.kg.qa.education.api.testapp.config.Configuration;
import ru.kshnykin.kg.qa.education.api.testapp.config.EnvironmentConfig;

import static io.restassured.config.EncoderConfig.encoderConfig;
import static java.nio.charset.StandardCharsets.UTF_8;
import static ru.kshnykin.kg.qa.education.api.testapp.api.ContentType.MULTI_FORM_DATA;

public abstract class ClientBase extends RestAssured {

    static {
        Configuration.init();
        EnvironmentConfig envConfig = Configuration.getEnvironmentConfig();
        RestAssured.baseURI = envConfig.getHost();
        RestAssured.basePath = envConfig.getBasePath();
        RestAssured.config = config()
                .encoderConfig(
                        encoderConfig()
                                .encodeContentTypeAs(MULTI_FORM_DATA, ContentType.TEXT)
                                .defaultContentCharset(UTF_8)
                );
    }

}
