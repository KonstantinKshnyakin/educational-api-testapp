package ru.kshnykin.kg.qa.education.api.testapp.api.controller;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.NotImplementedException;
import ru.kshnykin.kg.qa.education.api.testapp.api.ClientBase;
import ru.kshnykin.kg.qa.education.api.testapp.api.ContentType;
import ru.kshnykin.kg.qa.education.api.testapp.api.dto.Pet;

import java.util.Arrays;
import java.util.List;

public class PetController extends ClientBase {

    public static final String BASE_PATH = "/pet";
    public static final String UPLOAD_IMAGE_PATH = BASE_PATH + "/{petId}/uploadImage";
    public static final String FIND_BY_STATUS_PATH = BASE_PATH + "/findByStatus";

    private PetController() {
    }

    public static final PetController CONTROLLER = new PetController();

    public static PetController getInstance() {
        return CONTROLLER;
    }

    /**
     * Uploads an image
     * host/v2/pet/{petId}/uploadImage
     */
    public ValidatableResponse uploadImage(RequestSpecification spec) {
        return given()
                .log().all()
                .accept(ContentType.APP_JSON)
                .contentType(ContentType.MULTI_FORM_DATA)
                .spec(spec)
                .when()
                .post(UPLOAD_IMAGE_PATH)
                .then()
                .log().all();
    }

    /**
     * Add a new pet to the store
     * host/v2/pet
     */
    public ValidatableResponse addPet(RequestSpecification spec) {
        return RestAssured.given()
                .log().all()
                .contentType(ContentType.APP_JSON)
                .accept(ContentType.APP_JSON)
                .spec(spec)
                .when()
                .post(BASE_PATH)
                .then()
                .log().all();
    }

    public ValidatableResponse addPet(Object requestBody) {
        return RestAssured.given()
                .log().all()
                .contentType(ContentType.APP_JSON)
                .accept(ContentType.APP_JSON)
                .body(requestBody)
                .when()
                .post(BASE_PATH)
                .then()
                .log().all();
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
    public ValidatableResponse findByStatus(Object status) {
        return RestAssured.given()
                .log().all()
                .accept(ContentType.APP_JSON)
                .queryParam("status", status)
                .when()
                .get(FIND_BY_STATUS_PATH)
                .then()
                .log().all();
    }

    public List<Pet> findByStatusStep(Object status) {
        Pet[] pets = findByStatus(status)
                .statusCode(200)
                .contentType(ContentType.APP_JSON)
                .extract()
                .as(Pet[].class);
        return Arrays.asList(pets);
    }

    /**
     * Find pet by ID
     * host/v2/pet/{petId}
     */
    public ValidatableResponse getPetById(Object status) {
        throw new NotImplementedException();
    }

    /**
     * Updates a pet in the store with form data
     * host/v2/pet/{petId}
     */
    public ValidatableResponse updatePetWithForm(Object status) {
        throw new NotImplementedException();
    }

    /**
     * Deletes a pet
     * host/v2/pet/{petId}
     */
    public ValidatableResponse deletePet(Object status) {
        throw new NotImplementedException();
    }

}
