package ru.kshnykin.kg.qa.education.api.testapp.test.pet;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.internal.ValidatableResponseImpl;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.kshnykin.kg.qa.education.api.testapp.api.ContentType;
import ru.kshnykin.kg.qa.education.api.testapp.api.client.PetControllerClient;
import ru.kshnykin.kg.qa.education.api.testapp.api.dto.Pet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static ru.kshnykin.kg.qa.education.api.testapp.generators.PetReqGenerator.genPet;

@SuppressWarnings("UnusedReturnValue")
public class AddPetTests {

    public static final PetControllerClient.AddPet ENDPOINT = PetControllerClient.getAddPetEndpoint();

    @Test
    public void test0() {
        Pet reqBody = genPet();
        ValidatableResponseImpl response = ENDPOINT.getDefaultRequestWith(genPet());
        asserSuccessResponse(response, reqBody);
    }

    @Test
    public void test1() {
        Pet reqBody = genPet();
        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setBody(reqBody)
//                .addCookie("sda", "dsa")
//                .addFormParam("das", "sdasa")
//                .setAccept(ContentType.BINARY)
//                .setContentType(ContentType.ANY)
                .build();

        ValidatableResponseImpl response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserSuccessResponse(response, reqBody);
    }

    @Test
    public void test2() {
        Pet reqBody = genPet();
        ValidatableResponseImpl response = ENDPOINT.getDefaultRequestWith(reqBody);
        asserSuccessResponse(response, reqBody);
    }

    @Test
    public void test3() {
        Pet reqBody = genPet();
        ValidatableResponseImpl response = ENDPOINT.getDefaultRequestWith(reqBody);
        asserSuccessResponse(response, reqBody);
    }

    @Test
    public void test4() {
        Pet reqBody = genPet();
        ValidatableResponseImpl response = ENDPOINT.getDefaultRequestWith(reqBody);
        asserSuccessResponse(response, reqBody);
    }

    private ValidatableResponseImpl asserSuccessResponse(ValidatableResponseImpl response, Pet reqBody) {
        response.assertThat()
                .statusCode(200)
                .contentType(ContentType.APP_JSON);
        Pet respBody = response.originalResponse().as(Pet.class);
        asserResponseBody(reqBody, respBody);
        return response;
    }

    private void asserResponseBody(Pet reqBody, Pet respBody) {
        Assertions.assertAll(
                () -> assertThat("Pet.id", reqBody.getId(), is(not(respBody.getId()))),
                () -> assertThat("Pet.category", reqBody.getCategory(), is(notNullValue())),
                () -> assertThat("Pet.category.id", reqBody.getCategory().getId(), is(respBody.getCategory().getId())),
                () -> assertThat("Pet.category.name", reqBody.getCategory().getName(), is(respBody.getCategory().getName())),
                () -> assertThat("Pet.name", reqBody.getName(), is(respBody.getName())),
                () -> assertThat("Pet.photoUrls", reqBody.getPhotoUrls(), is(respBody.getPhotoUrls())),
                () -> assertThat("Pet.tags", reqBody.getTags(), is(notNullValue())),
                () -> assertThat("Pet.tags", reqBody.getTags(), is(respBody.getTags())),
                () -> assertThat("Pet.status", reqBody.getStatus(), is(respBody.getStatus()))
                );
    }

}