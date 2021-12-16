package ru.kshnykin.kg.qa.education.api.testapp.api.client;

import io.restassured.RestAssured;
import io.restassured.internal.ValidatableResponseImpl;
import io.restassured.specification.RequestSpecification;
import ru.kshnykin.kg.qa.education.api.testapp.api.ClientBase;
import ru.kshnykin.kg.qa.education.api.testapp.api.ContentType;

public class PetControllerClient {

    public static final String DEF_PATH = "/pet";

    public static AddPet getAddPetEndpoint() {
        return new AddPet();
    }
    /**
     * Uploads an image
     * host/v2/pet/uploadImage
     */
    public static class UploadImage extends ClientBase {

    }

    /**
     * Add a new pet to the store
     * host/v2/pet
     */
    public static class AddPet extends ClientBase {

        public ValidatableResponseImpl getDefaultRequestWith(RequestSpecification specification) {
            return (ValidatableResponseImpl) RestAssured.given()
                    .log().all()
                    .contentType(ContentType.APP_JSON)
                    .accept(ContentType.APP_JSON)
                    .spec(specification)
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
