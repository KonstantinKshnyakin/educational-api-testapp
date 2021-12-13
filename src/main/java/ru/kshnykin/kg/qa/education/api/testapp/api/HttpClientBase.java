package ru.kshnykin.kg.qa.education.api.testapp.api;

import io.restassured.RestAssured;

public abstract class HttpClientBase extends RestAssured{

    static {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "/v2";
    }

}
