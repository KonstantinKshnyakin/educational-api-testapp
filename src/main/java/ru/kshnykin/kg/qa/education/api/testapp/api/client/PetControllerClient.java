package ru.kshnykin.kg.qa.education.api.testapp.api.client;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import ru.kshnykin.kg.qa.education.api.testapp.api.ContentType;

public class PetControllerClient {

    public static final String DEFAULT_PATH = "/pet";

    public static Post_pet getPost_pet() {
        return new Post_pet();
    }
    /**
     * Uploads an image
     */
    public static class Post_pet_petId_UploadImage {

    }

    /**
     * Add a new pet to the store
     */
    public static class Post_pet {

        public ValidatableResponse getDefaultRequestWith(RequestSpecification specification) {
            return RestAssured.given()
                    .log().all()
                    .contentType(ContentType.APP_JSON)
                    .accept(ContentType.APP_JSON)
                    .spec(specification)
                    .when()
                    .post(DEFAULT_PATH)
                    .then()
                    .log().all();
        }

        public ValidatableResponse getDefaultRequestWith(Object requestBody) {
            return RestAssured.given()
                    .log().all()
                    .contentType(ContentType.APP_JSON)
                    .accept(ContentType.APP_JSON)
                    .body(requestBody)
                    .when()
                    .post(DEFAULT_PATH)
                    .then()
                    .log().all();
        }

    }

    /**
     * Update an existing pet
     */
    public static class Put_pet {

    }

    /**
     * Finds Pets by status
     */
    public static class Get_pet_findByStatus {

    }

    /**
     * Find pet by ID
     */
    public static class Get_pet_petId {

    }

    /**
     * Updates a pet in the store with form data
     */
    public static class Put_pet_petId {

    }

    /**
     * Deletes a pet
     */
    public static class Delete_pet_petId {

    }
}
