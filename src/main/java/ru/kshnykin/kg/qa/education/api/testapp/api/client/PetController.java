package ru.kshnykin.kg.qa.education.api.testapp.api.client;

import io.restassured.RestAssured;
import io.restassured.internal.ValidatableResponseImpl;
import io.restassured.specification.RequestSpecification;
import ru.kshnykin.kg.qa.education.api.testapp.api.ClientBase;
import ru.kshnykin.kg.qa.education.api.testapp.api.ContentType;

public class PetController {

    public static final String CONTROLLER_PATH = "/pet";

    public static AddPet getAddPetEndpoint() {
        return new AddPet();
    }

    public static UploadImage getUploadImageEndpoint() {
        return new UploadImage();
    }

    /**
     * Uploads an image
     * host/v2/pet/{petId}/uploadImage
     */
    public static class UploadImage extends ClientBase {

        public static final String DEF_PATH = CONTROLLER_PATH + "/{petId}/uploadImage";

        private UploadImage() {
        }

        public ValidatableResponseImpl getDefaultRequestWith(RequestSpecification spec) {
            return (ValidatableResponseImpl) given()
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

        public ValidatableResponseImpl getDefaultRequestWith(RequestSpecification spec) {
            return (ValidatableResponseImpl) RestAssured.given()
                    .log().all()
                    .contentType(ContentType.APP_JSON)
                    .accept(ContentType.APP_JSON)
                    .spec(spec)
                    .when()
                    .post(DEF_PATH)
                    .then()
                    .log().all();
        }

        public ValidatableResponseImpl getDefaultRequestWith(Object requestBody) {
            return (ValidatableResponseImpl) RestAssured.given()
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
