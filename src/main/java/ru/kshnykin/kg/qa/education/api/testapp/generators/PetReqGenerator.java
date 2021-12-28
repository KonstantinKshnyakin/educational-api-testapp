package ru.kshnykin.kg.qa.education.api.testapp.generators;

import lombok.experimental.UtilityClass;
import ru.kshnykin.kg.qa.education.api.testapp.api.dto.Category;
import ru.kshnykin.kg.qa.education.api.testapp.api.dto.Pet;
import ru.kshnykin.kg.qa.education.api.testapp.api.dto.Tag;

import java.util.Arrays;

import static ru.kshnykin.kg.qa.education.api.testapp.utils.RandomGenerator.genAlphabeticStr;
import static ru.kshnykin.kg.qa.education.api.testapp.utils.RandomGenerator.genPositiveInt;

@UtilityClass
public class PetReqGenerator {

    public static Pet genPet() {
        return new Pet()
                .setCategory(
                        new Category(genPositiveInt(), genAlphabeticStr(5))
                )
                .setName(genAlphabeticStr(5))
                .setPhotoUrls(
                        Arrays.asList(genAlphabeticStr(5), genAlphabeticStr(5), genAlphabeticStr(5))
                )
                .setTags(
                        Arrays.asList(
                                new Tag(genPositiveInt(), genAlphabeticStr(5)),
                                new Tag(genPositiveInt(), genAlphabeticStr(5)),
                                new Tag(genPositiveInt(), genAlphabeticStr(5)))
                )
                .setStatus(Pet.PetStatus.AVAILABLE);
    }

}