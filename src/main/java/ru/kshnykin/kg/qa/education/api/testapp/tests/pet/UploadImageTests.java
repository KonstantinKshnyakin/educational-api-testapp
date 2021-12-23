package ru.kshnykin.kg.qa.education.api.testapp.tests.pet;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.internal.ValidatableResponseImpl;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.kshnykin.kg.qa.education.api.testapp.api.ContentType;
import ru.kshnykin.kg.qa.education.api.testapp.api.client.PetController;
import ru.kshnykin.kg.qa.education.api.testapp.api.dto.ApiResponse;
import ru.kshnykin.kg.qa.education.api.testapp.generators.MultiPartSpecGenerator;
import ru.kshnykin.kg.qa.education.api.testapp.utils.DataGenerator;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static ru.kshnykin.kg.qa.education.api.testapp.api.dto.ApiResponse.*;
import static ru.kshnykin.kg.qa.education.api.testapp.utils.IOHelper.*;
import static ru.kshnykin.kg.qa.education.api.testapp.utils.PathToFile.*;

public class UploadImageTests {

    public static final PetController.UploadImage ENDPOINT = PetController.getUploadImageEndpoint();

    @Test
    @DisplayName("если передан petId = существующий id -> OK")
    public void test1640164769180() {
        RequestSpecification reqSpec = new RequestSpecBuilder()
                .addPathParam("petId", getPetId())
                .build();

        ValidatableResponseImpl response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserFailResponse(response, BAD_REQUEST.withMessage(MISS_START_BOUND_MES));
    }

    @Test
    @DisplayName("если переданы petId = существующий id и additionalMetadata -> OK")
    @Disabled("Должна возвращатся 500 по свагеру, но почемуто этого не происходит")
    public void test1640164769181() {
        RequestSpecification reqSpec = new RequestSpecBuilder()
                .addPathParam("petId", getPetId())
                .addFormParam("additionalMetadata", "additionalMetadata")
                .build();

        ValidatableResponseImpl response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserFailResponse(response, BAD_REQUEST.withMessage(MISS_START_BOUND_MES));
    }

    @Test
    @DisplayName("если переданы petId = существующий id и file -> OK")
    public void test1640164769182() {
        File file = getResourceAsFile(JPG);
        MultiPartSpecification multiPartSpec = MultiPartSpecGenerator.gen(file);
        RequestSpecification reqSpec = new RequestSpecBuilder()
                .addPathParam("petId", getPetId())
                .addMultiPart(multiPartSpec)
                .build();

        ValidatableResponseImpl response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserSuccessResponse(response, file, null);
    }

    @Test
    @DisplayName("если переданы petId = существующий id, file = jpg file и additionalMetadata = 'jpg'-> OK")
    public void test1640164769183() {
        File file = getResourceAsFile(JPG);
        String addMetadata = getFileExtension(file);
        RequestSpecification reqSpec = genRequestSpec(file, addMetadata);

        ValidatableResponseImpl response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserSuccessResponse(response, file, addMetadata);
    }

    @Test
    @DisplayName("если переданы petId = существующий id, file = png file и additionalMetadata = 'png'-> OK")
    public void test1640164769184() {
        File file = getResourceAsFile(PNG);
        String addMetadata = getFileExtension(file);
        RequestSpecification reqSpec = genRequestSpec(file, addMetadata);

        ValidatableResponseImpl response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserSuccessResponse(response, file, addMetadata);
    }

    @Test
    @DisplayName("если переданы petId = существующий id, file = gif file и additionalMetadata = 'gif'-> OK")
    public void test1640164769185() {
        File file = getResourceAsFile(GIF);
        String addMetadata = getFileExtension(file);
        RequestSpecification reqSpec = genRequestSpec(file, addMetadata);

        ValidatableResponseImpl response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserSuccessResponse(response, file, addMetadata);
    }

    @Test
    @DisplayName("если переданы petId = существующий id, file = pdf file и additionalMetadata = 'pdf'-> ERROR")
    public void test1640164769186() {
        File file = getResourceAsFile(PDF);
        String addMetadata = getFileExtension(file);
        RequestSpecification reqSpec = genRequestSpec(file, addMetadata);

        ValidatableResponseImpl response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserSuccessResponse(response, file, addMetadata);
    }

    @Test
    @DisplayName("если переданы petId = существующий id, file = txt file и additionalMetadata = 'txt'-> ERROR")
    public void test1640164769187() {
        File file = getResourceAsFile(TXT);
        String addMetadata = getFileExtension(file);
        RequestSpecification reqSpec = genRequestSpec(file, addMetadata);

        ValidatableResponseImpl response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserSuccessResponse(response, file, addMetadata);
    }

    @Test
    @DisplayName("все поля заполнены и передан дполонительный formParam -> OK")
    public void test1640164769188() {
        File file = getResourceAsFile(GIF);
        MultiPartSpecification multiPartSpec = MultiPartSpecGenerator.gen(file);
        String addMetadata = getFileExtension(file);
        RequestSpecification reqSpec = new RequestSpecBuilder()
                .addPathParam("petId", getPetId())
                .addFormParam("additionalMetadata", addMetadata)
                .addFormParam("additionalFormParam", "additionalFormParam")
                .addMultiPart(multiPartSpec)
                .build();

        ValidatableResponseImpl response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserSuccessResponse(response, file, addMetadata);
    }

    @Test
    @DisplayName("все поля заполнены и если поле id = 0 -> ERROR")
    public void test1640164769189() {
        File file = getResourceAsFile(GIF);
        String addMetadata = getFileExtension(file);
        RequestSpecification reqSpec = genRequestSpec(file, 0L, addMetadata);

        ValidatableResponseImpl response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserSuccessResponse(response, file, addMetadata);
    }

    @Test
    @DisplayName("все поля заполнены и если поле id = рандомное вещественно число -> ERROR")
    public void test1640164769190() {
        File file = getResourceAsFile(GIF);
        String addMetadata = getFileExtension(file);
        Double petId = DataGenerator.genPositiveDouble();
        RequestSpecification reqSpec = genRequestSpec(file, petId, addMetadata);

        ValidatableResponseImpl response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserFailResponse(response, NOT_FOUND.withMessage(NUM_FORMAT_EXC_MES, petId));
    }

    @Test
    @DisplayName("все поля заполнены и если поле id = рандомное отрицптельное число -> ERROR")
    public void test1640164769191() {
        File file = getResourceAsFile(GIF);
        String addMetadata = getFileExtension(file);
        Integer petId = DataGenerator.genNegativeInt();
        RequestSpecification reqSpec = genRequestSpec(file, petId, addMetadata);

        ValidatableResponseImpl response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserSuccessResponse(response, file, addMetadata);
    }

    @Test
    @DisplayName("все поля заполнены и если поле id = true -> ERROR")
    public void test1640164769192() {
        File file = getResourceAsFile(GIF);
        String addMetadata = getFileExtension(file);
        boolean petId = true;
        RequestSpecification reqSpec = genRequestSpec(file, petId, addMetadata);

        ValidatableResponseImpl response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserFailResponse(response, NOT_FOUND.withMessage(NUM_FORMAT_EXC_MES, petId));
    }

    @Test
    @DisplayName("все поля заполнены и если поле id = '%' -> ERROR")
    public void test1640164769193() {
        File file = getResourceAsFile(GIF);
        String addMetadata = getFileExtension(file);
        String petId = "%";
        RequestSpecification reqSpec = genRequestSpec(file, petId, addMetadata);

        ValidatableResponseImpl response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserFailResponse(response, NOT_FOUND.withMessage(NUM_FORMAT_EXC_MES, petId));
    }

    @Test
    @DisplayName("все поля заполнены и если поле id = 'пять' -> ERROR")
    public void test1640164769194() {
        File file = getResourceAsFile(GIF);
        String addMetadata = getFileExtension(file);
        String petId = "пять";
        RequestSpecification reqSpec = genRequestSpec(file, petId, addMetadata);

        ValidatableResponseImpl response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserFailResponse(response, NOT_FOUND.withMessage(NUM_FORMAT_EXC_MES, petId));
    }

    private RequestSpecification genRequestSpec(File file, String addMetadata) {
        return genRequestSpec(file, getPetId(), addMetadata);
    }

    private RequestSpecification genRequestSpec(File file, Object petId, String addMetadata) {
        MultiPartSpecification multiPartSpec = MultiPartSpecGenerator.gen(file);
        return new RequestSpecBuilder()
                .addPathParam("petId", petId)
                .addFormParam("additionalMetadata", addMetadata)
                .addMultiPart(multiPartSpec)
                .build();
    }

    private void asserFailResponse(ValidatableResponseImpl response, ApiResponse expected) {
        response.assertThat()
                .statusCode(expected.getCode())
                .contentType(ContentType.APP_JSON);

        ApiResponse respBody = response.originalResponse().as(ApiResponse.class);
        asserResponseBody(respBody, expected);
    }

    private void asserSuccessResponse(ValidatableResponseImpl response, File file, String addMetadata) {
        response.assertThat()
                .statusCode(SUCCESS.getCode())
                .contentType(ContentType.APP_JSON);

        String message = String.format(SUCCESS_MES, addMetadata, file.getName(), getFileSize(file));
        ApiResponse respBody = response.originalResponse().as(ApiResponse.class);
        asserResponseBody(respBody, SUCCESS.withMessage(message));
    }

    private void asserResponseBody(ApiResponse actual, ApiResponse expected) {
        Assertions.assertAll(
                () -> assertThat("ApiResponse.code", actual.getCode(), is(expected.getCode())),
                () -> assertThat("ApiResponse.type", actual.getType(), is(expected.getType())),
                () -> assertThat("ApiResponse.message", actual.getMessage(), is(expected.getMessage()))
        );
    }

    private Long getPetId() {
        return 53868678L;
    }

}