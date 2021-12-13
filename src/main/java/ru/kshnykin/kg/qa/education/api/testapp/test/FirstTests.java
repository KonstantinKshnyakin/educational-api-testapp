package ru.kshnykin.kg.qa.education.api.testapp.test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.kshnykin.kg.qa.education.api.testapp.api.ContentType;
import ru.kshnykin.kg.qa.education.api.testapp.api.client.PetControllerClient;
import ru.kshnykin.kg.qa.education.api.testapp.api.dto.Category;
import ru.kshnykin.kg.qa.education.api.testapp.api.dto.Pet;
import ru.kshnykin.kg.qa.education.api.testapp.api.dto.Tag;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@SuppressWarnings("UnusedReturnValue")
public class FirstTests {


    public static final PetControllerClient.Post_pet ENDPOINT = PetControllerClient.getPost_pet();
    public static final Pet PET = new Pet()
            .setId(0)
            .setCategory(new Category().setId(0).setName("string"))
            .setName("doggie")
            .setPhotoUrls(List.of("string"))
            .setTags(List.of(new Tag().setId(0).setName("string")))
            .setStatus(Pet.PetStatus.AVAILABLE);

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io";
//        RestAssured.baseURI = "https://petstore22.swagger33.io";
        RestAssured.basePath = "/v2";
    }

    @Test
    public void test0() {
        ValidatableResponse response = ENDPOINT.getDefaultRequestWith(PET);
        asserSuccessResponse(response);
    }

    @Test
    public void test1() {
        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setBody(PET)
//                .addCookie("sda", "dsa")
//                .addFormParam("das", "sdasa")
//                .setAccept(ContentType.BINARY)
//                .setContentType(ContentType.ANY)
                .build();

        ValidatableResponse response = ENDPOINT.getDefaultRequestWith(reqSpec);
        asserSuccessResponse(response);
    }

    @Test
    public void test2() {
        ValidatableResponse response = ENDPOINT.getDefaultRequestWith(PET);
        asserSuccessResponse(response);
    }

    @Test
    public void test3() {
        ValidatableResponse response = ENDPOINT.getDefaultRequestWith(PET);
        asserSuccessResponse(response);
    }

    @Test
    public void test4() {
        ValidatableResponse response = ENDPOINT.getDefaultRequestWith(PET);
        asserSuccessResponse(response);
    }

    private ValidatableResponse asserSuccessResponse(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .and()
                .body("status", not("0"))
                .body("status", is("available"));
        return response;
    }

}
