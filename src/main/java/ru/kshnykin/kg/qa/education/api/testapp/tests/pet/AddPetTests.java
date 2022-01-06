package ru.kshnykin.kg.qa.education.api.testapp.tests.pet;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import ru.kshnykin.kg.qa.education.api.testapp.api.ContentType;
import ru.kshnykin.kg.qa.education.api.testapp.api.controller.PetController;
import ru.kshnykin.kg.qa.education.api.testapp.api.dto.ApiResponse;
import ru.kshnykin.kg.qa.education.api.testapp.api.dto.Category;
import ru.kshnykin.kg.qa.education.api.testapp.api.dto.Pet;
import ru.kshnykin.kg.qa.education.api.testapp.api.dto.Tag;
import ru.kshnykin.kg.qa.education.api.testapp.junit.api.annotations.Test;
import ru.kshnykin.kg.qa.education.api.testapp.junit.api.annotations.TestClass;
import ru.kshnykin.kg.qa.education.api.testapp.junit.api.annotations.tags.PetAddTag;
import ru.kshnykin.kg.qa.education.api.testapp.tests.BaseTest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static ru.kshnykin.kg.qa.education.api.testapp.generators.PetReqGenerator.genPet;
import static ru.kshnykin.kg.qa.education.api.testapp.utils.RandomGenerator.*;

@PetAddTag
@TestClass(title = "Add a new pet to the store - host/v2/pet")
public class AddPetTests extends BaseTest {

    public static final PetController PET_CONTROLLER = PetController.getInstance();

    @Test(title = "отправлен не заполненный объект -> OK")
    public void test1640600173303() {
        Pet reqBody = new Pet();
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены, кроме поля id -> OK")
    public void test1640600173304() {
        Pet reqBody = genPet()
                .setId(null);
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены, кроме поля category -> OK")
    public void test1640600173305() {
        Pet reqBody = genPet()
                .setCategory(null);
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены, кроме поля category.id -> OK")
    public void test1640600173306() {
        Pet reqBody = genPet();
        reqBody.getCategory().setId(null);
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены, кроме поля category.name -> OK")
    public void test1640600173307() {
        Pet reqBody = genPet();
        reqBody.getCategory().setName(null);
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены, кроме поля name -> OK")
    public void test1640600173308() {
        Pet reqBody = genPet()
                .setName(null);
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены, кроме поля photoUrls -> OK")
    public void test1640600173309() {
        Pet reqBody = genPet()
                .setPhotoUrls(null);
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены, кроме поля tags -> OK")
    public void test1640600173310() {
        Pet reqBody = genPet()
                .setTags(null);
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены, кроме поля status -> OK")
    public void test1640600173311() {
        Pet reqBody = genPet()
                .setStatus(null);
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "заполнено только поле id -> OK")
    public void test1640600173312() {
        Pet reqBody = new Pet()
                .setId(genPositiveInt());
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "заполнено только поле category -> OK")
    public void test1640600173313() {
        Pet reqBody = new Pet()
                .setCategory(new Category(genPositiveInt(), genAlphabeticStr(5)));
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "заполнено только поле category.id -> OK")
    public void test1640600173314() {
        Pet reqBody = new Pet()
                .setCategory(new Category().setId(genPositiveInt()));
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "заполнено только поле category.name -> OK")
    public void test1640600173315() {
        Pet reqBody = new Pet()
                .setCategory(new Category().setName(genAlphabeticStr(5)));
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "заполнено только поле name -> OK")
    public void test1640600173316() {
        Pet reqBody = new Pet()
                .setName(genAlphabeticStr(5));
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "заполнено только поле photoUrls -> OK")
    public void test1640600173317() {
        Pet reqBody = new Pet()
                .setPhotoUrls(genPet().getPhotoUrls());
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "заполнено только поле tags -> OK")
    public void test1640600173318() {
        Pet reqBody = new Pet()
                .setTags(genPet().getTags());
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "заполнено только поле status -> OK")
    public void test1640600173319() {
        Pet reqBody = new Pet()
                .setStatus(genPet().getStatus());
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и поле id = радомное целое число -> ОК")
    public void test1640600173320() {
        Pet reqBody = genPet()
                .setId(genPositiveInt());
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле id = 0 -> ОК")
    public void test1640600173321() {
        Pet reqBody = genPet()
                .setId(0);
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и поле id = рандомное вещественно число -> ERROR")
    public void test1640600173322() {
        Pet reqBody = genPet()
                .setId(genPositiveDouble());
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и поле id = рандомное отрицптельное число -> OK")
    public void test1640600173323() {
        Pet reqBody = genPet()
                .setId(genNegativeInt());
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и поле id = true -> ERROR")
    public void test1640600173324() {
        Pet reqBody = genPet()
                .setId(true);
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertFailResponse(response);
    }

    @Test(title = "все поля заполнены и поле id = '%' -> ERROR")
    public void test1640600173325() {
        Pet reqBody = genPet()
                .setId("%");
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertFailResponse(response);
    }

    @Test(title = "все поля заполнены и поле id = 'пять' -> ERROR")
    public void test1640600173326() {
        Pet reqBody = genPet()
                .setId("пять");
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertFailResponse(response);
    }

    @Test(title = "все поля заполнены и если поле category.id = радомное целое число -> ОК")
    public void test1640600173327() {
        Pet reqBody = genPet();
        reqBody.getCategory().setId(genPositiveInt());
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле category.id = 0 -> ОК")
    public void test1640600173328() {
        Pet reqBody = genPet();
        reqBody.getCategory().setId(0);
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле category.id = рандомное вещественно число -> ОК")
    public void test1640600173329() {
        Pet reqBody = genPet();
        reqBody.getCategory().setId(genPositiveDouble());
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле category.id = рандомное отрицптельное число -> ОК")
    public void test1640600173330() {
        Pet reqBody = genPet();
        reqBody.getCategory().setId(genNegativeInt());
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле category.id = true -> ERROR")
    public void test1640600173331() {
        Pet reqBody = genPet();
        reqBody.getCategory().setId(true);
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertFailResponse(response);
    }

    @Test(title = "все поля заполнены и если поле category.id = '%' -> ERROR")
    public void test1640600173332() {
        Pet reqBody = genPet();
        reqBody.getCategory().setId("%");
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertFailResponse(response);
    }

    @Test(title = "все поля заполнены и если поле category.id = 'пять' -> ERROR")
    public void test1640600173333() {
        Pet reqBody = genPet();
        reqBody.getCategory().setId("пять");
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertFailResponse(response);
    }

    @Test(title = "все поля заполнены и если поле category.name = радомная строка 5 букв -> ОК")
    public void test1640600173334() {
        Pet reqBody = genPet();
        reqBody.getCategory().setName(genAlphabeticStr(5));
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле category.name = рандомная строка 100 букв -> ОК")
    public void test1640600173335() {
        Pet reqBody = genPet();
        reqBody.getCategory().setName(genAlphabeticStr(100));
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле category.name = '%' -> ОК")
    public void test1640600173336() {
        Pet reqBody = genPet();
        reqBody.getCategory().setName("%");
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле category.name = '|!\"№;:?*()_+.,%' -> ОК")
    public void test1640600173337() {
        Pet reqBody = genPet();
        reqBody.getCategory().setName("|!\"№;:?*()_+.,%");
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле category.name = true -> OK")
    public void test1640600173338() {
        Pet reqBody = genPet();
        reqBody.getCategory().setName(true);
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле category.name = радомное целое число -> OK")
    public void test1640600173339() {
        Pet reqBody = genPet();
        reqBody.getCategory().setName(genPositiveInt());
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле category.name = рандомное вещественно число -> OK")
    public void test1640600173340() {
        Pet reqBody = genPet();
        reqBody.getCategory().setName(genPositiveDouble());
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле name = радомная строка 5 букв -> ОК")
    public void test1640600173341() {
        Pet reqBody = genPet()
                .setName(genAlphabeticStr(5));
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле name = рандомная строка 100 букв -> ОК")
    public void test1640600173342() {
        Pet reqBody = genPet()
                .setName(genAlphabeticStr(100));
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле name = '%' -> ОК")
    public void test1640600173343() {
        Pet reqBody = genPet()
                .setName("%");
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле name = '|!\"№;:?*()_+.,%' -> ОК")
    public void test1640600173344() {
        Pet reqBody = genPet()
                .setName("%");
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле name = true -> ОК")
    public void test1640600173345() {
        Pet reqBody = genPet()
                .setName(true);
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле name = радомное целое число -> ОК")
    public void test1640600173346() {
        Pet reqBody = genPet()
                .setName(genPositiveInt());
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле name = рандомное вещественно число -> ОК")
    public void test1640600173347() {
        Pet reqBody = genPet()
                .setName(genPositiveDouble());
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле photoUrls = [радомная строка 5 букв] -> ОК")
    public void test1640600173349() {
        Pet reqBody = genPet()
                .setPhotoUrls(List.of(genAlphabeticStr(5)));
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле photoUrls = [100 ссылок на картинки] -> ОК")
    public void test1640600173350() {
        List<Object> photoUrls = genList(() -> genAlphabeticStr(5), 100);
        Pet reqBody = genPet()
                .setPhotoUrls(photoUrls);
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле photoUrls = ['%'] -> ОК")
    public void test1640600173352() {
        Pet reqBody = genPet()
                .setPhotoUrls(List.of("%"));
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле photoUrls = ['|','!','\"','№',';',':','?','*','%'] -> ОК")
    public void test1640600173353() {
        Pet reqBody = genPet()
                .setPhotoUrls(List.of("|", "!", "\"", "№", ";", ":", "?", "*", "%"));
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле photoUrls = [радомное целое число] -> ОК")
    public void test1640600173354() {
        Pet reqBody = genPet()
                .setPhotoUrls(List.of(genPositiveInt()));
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле photoUrls = [рандомное вещественно число] -> ОК")
    public void test1640600173355() {
        Pet reqBody = genPet()
                .setPhotoUrls(List.of(genPositiveDouble()));
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле photoUrls = [true] -> OK")
    public void test1640600173356() {
        Pet reqBody = genPet()
                .setPhotoUrls(List.of(true));
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле tags = [1 валидный обект] -> ОК")
    public void test1640600173357() {
        Pet reqBody = genPet()
                .setTags(Arrays.asList(new Tag(genPositiveInt(), genAlphabeticStr(5))));
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле tags = [100 валидных обектов] -> ОК")
    public void test1640600173358() {
        List<Tag> tags = genList(() -> new Tag(genPositiveInt(), genAlphabeticStr(5)), 100);
        Pet reqBody = genPet()
                .setTags(tags);
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле tags[0].id = радомное целое число -> ОК")
    public void test1640600173359() {
        Pet reqBody = genPet();
        reqBody.getTags().get(0).setId(genPositiveInt());
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле tags[0].id = 0 -> OK")
    public void test1640600173360() {
        Pet reqBody = genPet();
        reqBody.getTags().get(0).setId(0);
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле tags[0].id = рандомное вещественно число -> OK")
    public void test1640600173361() {
        Pet reqBody = genPet();
        reqBody.getTags().get(0).setId(genPositiveDouble());
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле tags[0].id = рандомное отрицптельное число -> ERROR")
    public void test1640600173362() {
        Pet reqBody = genPet();
        reqBody.getTags().get(0).setId(genNegativeInt());
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле tags[0].id = true -> ERROR")
    public void test1640600173363() {
        Pet reqBody = genPet();
        reqBody.getTags().get(0).setId(true);
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertFailResponse(response);
    }

    @Test(title = "все поля заполнены и если поле tags[0].id = '%' -> ERROR")
    public void test1640600173364() {
        Pet reqBody = genPet();
        reqBody.getTags().get(0).setId("%");
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertFailResponse(response);
    }

    @Test(title = "все поля заполнены и если поле tags[0].id = 'пять' -> ERROR")
    public void test1640600173365() {
        Pet reqBody = genPet();
        reqBody.getTags().get(0).setId("пять");
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertFailResponse(response);
    }

    @Test(title = "все поля заполнены и если поле tags[0].name = радомная строка 5 букв -> ОК")
    public void test1640600173366() {
        Pet reqBody = genPet();
        reqBody.getTags().get(0).setName(genAlphabeticStr(5));
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле tags[0].name = рандомная строка 100 букв -> ОК")
    public void test1640600173367() {
        Pet reqBody = genPet();
        reqBody.getTags().get(0).setName(genAlphabeticStr(100));
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле tags[0].name = '%' -> ОК")
    public void test1640600173368() {
        Pet reqBody = genPet();
        reqBody.getTags().get(0).setName("%");
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле tags[0].name = '|!\"№;:?*()_+.,%' -> ОК")
    public void test1640600173369() {
        Pet reqBody = genPet();
        reqBody.getTags().get(0).setName("|!\"№;:?*()_+.,%");
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле tags[0].name = true -> ОК")
    public void test1640600173370() {
        Pet reqBody = genPet();
        reqBody.getTags().get(0).setName("|!\"№;:?*()_+.,%");
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле tags[0].name = радомное целое число -> ОК")
    public void test1640600173371() {
        Pet reqBody = genPet();
        reqBody.getTags().get(0).setName("|!\"№;:?*()_+.,%");
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле tags[0].name = рандомное вещественно число -> OK")
    public void test1640600173372() {
        Pet reqBody = genPet();
        reqBody.getTags().get(0).setName(genPositiveDouble());
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле status = 'available' -> ОК")
    public void test1640600173373() {
        Pet reqBody = genPet()
                .setStatus(Pet.PetStatus.AVAILABLE);
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле status = 'pending' -> ОК")
    public void test1640600173374() {
        Pet reqBody = genPet()
                .setStatus(Pet.PetStatus.PENDING);
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле status = 'sold' -> ОК")
    public void test1640600173375() {
        Pet reqBody = genPet()
                .setStatus(Pet.PetStatus.SOLD);
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле status = радомная строка 5 букв -> OK")
    public void test1640600173376() {
        Pet reqBody = genPet()
                .setStatus(genAlphabeticStr(5));
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле status = рандомная строка 100 букв -> OK")
    public void test1640600173377() {
        Pet reqBody = genPet()
                .setStatus(genAlphabeticStr(100));
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле status = '%' -> OK")
    public void test1640600173378() {
        Pet reqBody = genPet()
                .setStatus("%");
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле status = '|!\"№;:?*()_+.,%' -> OK")
    public void test1640600173379() {
        Pet reqBody = genPet()
                .setStatus("|!\"№;:?*()_+.,%");
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле status = true -> ERROR")
    public void test1640600173380() {
        Pet reqBody = genPet()
                .setStatus(true);
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле status = радомное целое число -> ERROR")
    public void test1640600173381() {
        Pet reqBody = genPet()
                .setStatus(genPositiveInt());
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    @Test(title = "все поля заполнены и если поле status = рандомное вещественно число -> ERROR")
    public void test1640600173382() {
        Pet reqBody = genPet()
                .setStatus(genPositiveDouble());
        ValidatableResponse response = PET_CONTROLLER.addPet(reqBody);

        assertSuccessResponse(response, reqBody);
    }

    private void assertSuccessResponse(ValidatableResponse response, Pet reqBody) {
        Pet respBody = response.assertThat()
                .statusCode(200)
                .contentType(ContentType.APP_JSON)
                .extract()
                .as(Pet.class);

        assertPetResponseBody(respBody, reqBody);
    }

    private void assertFailResponse(ValidatableResponse response) {
        ApiResponse expected = ApiResponse.ERROR;
        ApiResponse respBody = response.assertThat()
                .statusCode(expected.getCode())
                .contentType(ContentType.APP_JSON)
                .extract()
                .as(ApiResponse.class);

        assertApiResponseBody(respBody, expected);
    }

    private void assertApiResponseBody(ApiResponse actual, ApiResponse expected) {
        Assertions.assertAll(
                () -> assertThat("ApiResponse.code", actual.getCode(), is(expected.getCode())),
                () -> assertThat("ApiResponse.type", actual.getType(), is(expected.getType())),
                () -> assertThat("ApiResponse.message", actual.getMessage(), is(expected.getMessage()))
        );
    }

    private void assertPetResponseBody(Pet respBody, Pet reqBody) {
        Assertions.assertAll(
                () -> {
                    assertThat("Pet.id", respBody.getId(), is(notNullValue()));
                    Object reqId = reqBody.getId();
                    if (reqId != null && (((reqId instanceof Integer) && ((Integer) reqId) > 0) || (reqId instanceof Double))) {
                        assertThat("Pet.id", toStr(respBody.getId()), is(toStr(replaceAfterDotIfDouble(reqId))));
                    }
                },
                () -> {
                    Category reqCategory = reqBody.getCategory();
                    if (reqCategory != null) {
                        Category respCategory = respBody.getCategory();
                        if (reqCategory.getId() == null) {
                            assertThat("Pet.category.id", respCategory.getId(), is(0));
                        } else {
                            assertThat("Pet.category.id", respCategory.getId(), is(replaceAfterDotIfDouble(reqCategory.getId())));
                        }
                        assertThat("Pet.category.name", toStr(respCategory.getName()), is(toStr(reqCategory.getName())));
                    }
                },
                () -> assertThat("Pet.name", toStr(respBody.getName()), is(toStr(reqBody.getName()))),
                () -> {
                    List<Object> reqPhotoUrls = reqBody.getPhotoUrls();
                    if (reqPhotoUrls != null && reqPhotoUrls.size() > 0) {
                        reqPhotoUrls.forEach(reqPhotoUrl ->
                                assertThat("Pet.photoUrl", respBody.getPhotoUrls(), is(hasItem(toStr(reqPhotoUrl))))
                        );
                    }
                },
                () -> {
                    List<Tag> reqTags = reqBody.getTags();
                    List<Tag> respTags = respBody.getTags();
                    if (reqTags != null && reqTags.size() > 0) {
                        reqTags.sort(Comparator.comparing(tag -> toStr(tag.getName())));
                        respTags.sort(Comparator.comparing(tag -> toStr(tag.getName())));
                        for (int i = 0; i < reqTags.size(); i++) {
                            Tag respTag = respTags.get(i);
                            Tag reqTag = reqTags.get(i);
                            assertThat("Pet.tag.id", toStr(respTag.getId()), is(toStr(replaceAfterDotIfDouble(reqTag.getId()))));
                            assertThat("Pet.tag.name", toStr(respTag.getName()), is(toStr(reqTag.getName())));
                        }
                    }
                },
                () -> assertThat("Pet.status", toStr(respBody.getStatus()), is(toStr(reqBody.getStatus())))
        );
    }

}