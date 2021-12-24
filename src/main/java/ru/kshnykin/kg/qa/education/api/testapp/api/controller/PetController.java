package ru.kshnykin.kg.qa.education.api.testapp.api.controller;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import ru.kshnykin.kg.qa.education.api.testapp.api.ClientBase;
import ru.kshnykin.kg.qa.education.api.testapp.api.ContentType;
import ru.kshnykin.kg.qa.education.api.testapp.api.dto.Pet;

import java.util.Arrays;
import java.util.List;

public class PetController {

    public static final String CONTROLLER_PATH = "/pet";

    public static AddPet getAddPetEndpoint() {
        return new AddPet();
    }

    public static UploadImage getUploadImageEndpoint() {
        return new UploadImage();
    }

    public static FindPetsByStatus getFindPetsByStatusEndpoint() {
        return new FindPetsByStatus();
    }

    /**
     * Uploads an image
     * host/v2/pet/{petId}/uploadImage
     */
    public static class UploadImage extends ClientBase {

        public static final String DEF_PATH = CONTROLLER_PATH + "/{petId}/uploadImage";

        private UploadImage() {
        }

        public ValidatableResponse getDefaultRequestWith(RequestSpecification spec) {
            return given()
                    .log().all()
                    .accept(ContentType.APP_JSON)
                    .contentType(ContentType.MULTI_FORM_DATA)
                    .spec(spec)
                    .when()
                    .post(DEF_PATH)
                    .then()
                    .log().all();
        }

    }

    /**
     * Add a new pet to the store
     * host/v2/pet
     */
    public static class AddPet extends ClientBase {

        public static final String DEF_PATH = CONTROLLER_PATH;

        private AddPet() {
        }

        public ValidatableResponse getDefaultRequestWith(RequestSpecification spec) {
            return RestAssured.given()
                    .log().all()
                    .contentType(ContentType.APP_JSON)
                    .accept(ContentType.APP_JSON)
                    .spec(spec)
                    .when()
                    .post(DEF_PATH)
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
                    .post(DEF_PATH)
                    .then()
                    .log().all();
        }

    }

    /**
     * Update an existing pet
     * host/v2/pet
     */
    public static class UpdatePet extends ClientBase {

    }

    /**
     * Finds Pets by status
     * host/v2/pet/findByStatus
     */
    public static class FindPetsByStatus extends ClientBase {

        public static final String DEF_PATH = CONTROLLER_PATH + "/findByStatus";

        private FindPetsByStatus() {
        }

        public ValidatableResponse getDefaultRequestWith(Object status) {
            return RestAssured.given()
                    .log().all()
                    .accept(ContentType.APP_JSON)
                    .queryParam("status", status)
                    .when()
                    .get(DEF_PATH)
                    .then()
                    .log().all();
        }

        public List<Pet> getDefaultRequestStep(Object status) {
            Pet[] pets = getDefaultRequestWith(status)
                    .statusCode(200)
                    .contentType(ContentType.APP_JSON)
                    .extract()
                    .as(Pet[].class);
            return Arrays.asList(pets);
        }

    }

    /**
     * Find pet by ID
     * host/v2/pet/{petId}
     */
    public static class GetPetById extends ClientBase {

    }

    /**
     * Updates a pet in the store with form data
     * host/v2/pet/{petId}
     */
    public static class UpdatePetWithForm extends ClientBase {

    }

    /**
     * Deletes a pet
     * host/v2/pet/{petId}
     */
    public static class DeletePet extends ClientBase {

    }
}
