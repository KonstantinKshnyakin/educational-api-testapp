package ru.kshnykin.kg.qa.education.api.testapp.api;

import com.github.dzieciou.testing.curl.CurlRestAssuredConfigFactory;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.config.EncoderConfig.encoderConfig;
import static java.nio.charset.StandardCharsets.UTF_8;
import static ru.kshnykin.kg.qa.education.api.testapp.api.ContentType.MULTI_FORM_DATA;

public abstract class ClientBase extends RestAssured {

    static {
        RestAssured.baseURI = "https://petstore.swagger.io";
//        RestAssured.baseURI = "https://petstore22.swagger33.io";
        RestAssured.basePath = "/v2";
        RestAssured.config = CurlRestAssuredConfigFactory.createConfig()
//        RestAssured.config = config()
                .encoderConfig(
                        encoderConfig()
                                .encodeContentTypeAs(MULTI_FORM_DATA, ContentType.TEXT)
                                .defaultContentCharset(UTF_8)
                );
    }

}
