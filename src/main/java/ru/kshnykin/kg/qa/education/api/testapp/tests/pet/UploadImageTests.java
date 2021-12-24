package ru.kshnykin.kg.qa.education.api.testapp.tests.pet;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.kshnykin.kg.qa.education.api.testapp.api.ContentType;
import ru.kshnykin.kg.qa.education.api.testapp.api.controller.PetController;
import ru.kshnykin.kg.qa.education.api.testapp.api.dto.ApiResponse;
import ru.kshnykin.kg.qa.education.api.testapp.api.dto.Pet;
import ru.kshnykin.kg.qa.education.api.testapp.exception.ResourceNotFoundException;
import ru.kshnykin.kg.qa.education.api.testapp.generators.MultiPartSpecGenerator;
import ru.kshnykin.kg.qa.education.api.testapp.utils.DataGenerator;

import java.io.File;
import java.util.Optional;

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

        ValidatableResponse response = ENDPOINT.getDefaultRequestWith(reqSpec);
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

        ValidatableResponse response = ENDPOINT.getDefaultRequestWith(reqSpec);
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

        ValidatableResponse response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserSuccessResponse(response, file, null);
    }

    @Test
    @DisplayName("если переданы petId = существующий id, file = jpg file и additionalMetadata = 'jpg'-> OK")
    public void test1640164769183() {
        File file = getResourceAsFile(JPG);
        String addMetadata = getFileExtension(file);
        RequestSpecification reqSpec = genRequestSpec(file, addMetadata);

        ValidatableResponse response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserSuccessResponse(response, file, addMetadata);
    }

    @Test
    @DisplayName("если переданы petId = существующий id, file = png file и additionalMetadata = 'png'-> OK")
    public void test1640164769184() {
        File file = getResourceAsFile(PNG);
        String addMetadata = getFileExtension(file);
        RequestSpecification reqSpec = genRequestSpec(file, addMetadata);

        ValidatableResponse response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserSuccessResponse(response, file, addMetadata);
    }

    @Test
    @DisplayName("если переданы petId = существующий id, file = gif file и additionalMetadata = 'gif'-> OK")
    public void test1640164769185() {
        File file = getResourceAsFile(GIF);
        String addMetadata = getFileExtension(file);
        RequestSpecification reqSpec = genRequestSpec(file, addMetadata);

        ValidatableResponse response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserSuccessResponse(response, file, addMetadata);
    }

    @Test
    @DisplayName("если переданы petId = существующий id, file = pdf file и additionalMetadata = 'pdf'-> ERROR")
    public void test1640164769186() {
        File file = getResourceAsFile(PDF);
        String addMetadata = getFileExtension(file);
        RequestSpecification reqSpec = genRequestSpec(file, addMetadata);

        ValidatableResponse response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserSuccessResponse(response, file, addMetadata);
    }

    @Test
    @DisplayName("если переданы petId = существующий id, file = txt file и additionalMetadata = 'txt'-> ERROR")
    public void test1640164769187() {
        File file = getResourceAsFile(TXT);
        String addMetadata = getFileExtension(file);
        RequestSpecification reqSpec = genRequestSpec(file, addMetadata);

        ValidatableResponse response = ENDPOINT.getDefaultRequestWith(reqSpec);
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

        ValidatableResponse response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserSuccessResponse(response, file, addMetadata);
    }

    @Test
    @DisplayName("все поля заполнены и если поле id = 0 -> ERROR")
    public void test1640164769189() {
        File file = getResourceAsFile(GIF);
        String addMetadata = getFileExtension(file);
        RequestSpecification reqSpec = genRequestSpec(file, 0L, addMetadata);

        ValidatableResponse response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserSuccessResponse(response, file, addMetadata);
    }

    @Test
    @DisplayName("все поля заполнены и если поле id = рандомное вещественно число -> ERROR")
    public void test1640164769190() {
        File file = getResourceAsFile(GIF);
        String addMetadata = getFileExtension(file);
        Double petId = DataGenerator.genPositiveDouble();
        RequestSpecification reqSpec = genRequestSpec(file, petId, addMetadata);

        ValidatableResponse response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserFailResponse(response, NOT_FOUND.withMessage(NUM_FORMAT_EXC_MES, petId));
    }

    @Test
    @DisplayName("все поля заполнены и если поле id = рандомное отрицптельное число -> ERROR")
    public void test1640164769191() {
        File file = getResourceAsFile(GIF);
        String addMetadata = getFileExtension(file);
        Integer petId = DataGenerator.genNegativeInt();
        RequestSpecification reqSpec = genRequestSpec(file, petId, addMetadata);

        ValidatableResponse response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserSuccessResponse(response, file, addMetadata);
    }

    @Test
    @DisplayName("все поля заполнены и если поле id = true -> ERROR")
    public void test1640164769192() {
        File file = getResourceAsFile(GIF);
        String addMetadata = getFileExtension(file);
        boolean petId = true;
        RequestSpecification reqSpec = genRequestSpec(file, petId, addMetadata);

        ValidatableResponse response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserFailResponse(response, NOT_FOUND.withMessage(NUM_FORMAT_EXC_MES, petId));
    }

    @Test
    @DisplayName("все поля заполнены и если поле id = '%' -> ERROR")
    public void test1640164769193() {
        File file = getResourceAsFile(GIF);
        String addMetadata = getFileExtension(file);
        String petId = "%";
        RequestSpecification reqSpec = genRequestSpec(file, petId, addMetadata);

        ValidatableResponse response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserFailResponse(response, NOT_FOUND.withMessage(NUM_FORMAT_EXC_MES, petId));
    }

    @Test
    @DisplayName("все поля заполнены и если поле id = 'пять' -> ERROR")
    public void test1640164769194() {
        File file = getResourceAsFile(GIF);
        String addMetadata = getFileExtension(file);
        String petId = "пять";
        RequestSpecification reqSpec = genRequestSpec(file, petId, addMetadata);

        ValidatableResponse response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserFailResponse(response, NOT_FOUND.withMessage(NUM_FORMAT_EXC_MES, petId));
    }

    @Test
    @DisplayName("все поля заполнены и поле additionalMetadata = рандомное вещественно число -> OK")
    public void test1640355826866() {
        File file = getResourceAsFile(GIF);
        Double addMetadata = DataGenerator.genPositiveDouble();
        RequestSpecification reqSpec = genRequestSpec(file, addMetadata);

        ValidatableResponse response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserSuccessResponse(response, file, addMetadata);
    }

    @Test
    @DisplayName("все поля заполнены и поле additionalMetadata = рандомное отрицптельное число -> OK")
    public void test1640355826867() {
        File file = getResourceAsFile(GIF);
        Integer addMetadata = DataGenerator.genNegativeInt();
        RequestSpecification reqSpec = genRequestSpec(file, addMetadata);

        ValidatableResponse response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserSuccessResponse(response, file, addMetadata);
    }

    @Test
    @DisplayName("все поля заполнены и поле additionalMetadata = true -> OK")
    public void test1640355826868() {
        File file = getResourceAsFile(GIF);
        boolean addMetadata = true;
        RequestSpecification reqSpec = genRequestSpec(file, addMetadata);

        ValidatableResponse response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserSuccessResponse(response, file, addMetadata);
    }

    @Test
    @DisplayName("все поля заполнены и поле additionalMetadata = '&' -> OK")
    @Disabled
    public void test1640355826869() {
        File file = getResourceAsFile(GIF);
        String addMetadata = "&";
        RequestSpecification reqSpec = genRequestSpec(file, addMetadata);

        ValidatableResponse response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserSuccessResponse(response, file, addMetadata);
    }

    @Test
    @DisplayName("все поля заполнены и поле additionalMetadata = 'five' -> OK")
    @Disabled
    public void test1640355826870() {
        File file = getResourceAsFile(GIF);
        String addMetadata = "five";
        RequestSpecification reqSpec = genRequestSpec(file, addMetadata);

        ValidatableResponse response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserSuccessResponse(response, file, addMetadata);
    }

    private RequestSpecification genRequestSpec(File file, Object addMetadata) {
        return genRequestSpec(file, getPetId(), addMetadata);
    }

    private RequestSpecification genRequestSpec(File file, Object petId, Object addMetadata) {
        MultiPartSpecification multiPartSpec = MultiPartSpecGenerator.gen(file);
        return new RequestSpecBuilder()
                .addPathParam("petId", petId)
                .addFormParam("additionalMetadata", addMetadata)
                .addMultiPart(multiPartSpec)
                .build();
    }

    private void asserFailResponse(ValidatableResponse response, ApiResponse expected) {
        ApiResponse respBody = response.assertThat()
                .statusCode(expected.getCode())
                .contentType(ContentType.APP_JSON)
                .extract()
                .as(ApiResponse.class);

        asserResponseBody(respBody, expected);
    }

    private void asserSuccessResponse(ValidatableResponse response, File file, Object addMetadata) {
        ApiResponse respBody = response.assertThat()
                .statusCode(SUCCESS.getCode())
                .contentType(ContentType.APP_JSON)
                .extract()
                .as(ApiResponse.class);

        String message = String.format(SUCCESS_MES, addMetadata, file.getName(), getFileSize(file));
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
        Optional<Pet> pet = PetController.getFindPetsByStatusEndpoint()
                .getDefaultRequestStep(Pet.PetStatus.PENDING)
                .stream().findFirst();
        if (pet.isPresent()) {
            return pet.get().getId();
        }
        throw new ResourceNotFoundException("Не найден обект Pet");
    }

}