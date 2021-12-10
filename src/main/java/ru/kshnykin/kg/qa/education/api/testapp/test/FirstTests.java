package ru.kshnykin.kg.qa.education.api.testapp.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.kshnykin.kg.qa.education.api.testapp.api.dto.Category;
import ru.kshnykin.kg.qa.education.api.testapp.api.dto.Pet;
import ru.kshnykin.kg.qa.education.api.testapp.api.dto.Tag;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


public class FirstTests {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "/v2";
    }

    @Test
    public void test() {
        Pet pet = new Pet()
                .setId(0)
                .setCategory(new Category().setId(0).setName("string"))
                .setName("doggie")
                .setPhotoUrls(List.of("string"))
                .setTags(List.of(new Tag().setId(0).setName("string")))
                .setStatus(Pet.PetStatus.AVAILABLE);

        given().log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(pet)
                .when()
                .post("/pet")
                .then().
                log().all().
                assertThat().statusCode(200).
                and()
                .body("status", not("0"))
                .body("status", is("available"));
    }

}
