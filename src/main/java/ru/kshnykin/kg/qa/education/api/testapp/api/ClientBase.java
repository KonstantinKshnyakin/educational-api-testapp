package ru.kshnykin.kg.qa.education.api.testapp.api;

import io.restassured.RestAssured;

public abstract class ClientBase extends RestAssured {

    static {
        RestAssured.baseURI = "https://petstore.swagger.io";
//        RestAssured.baseURI = "https://petstore22.swagger33.io";
        RestAssured.basePath = "/v2";
    }

}
