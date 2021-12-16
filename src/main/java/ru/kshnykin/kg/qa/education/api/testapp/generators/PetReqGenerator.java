package ru.kshnykin.kg.qa.education.api.testapp.generators;

import lombok.experimental.UtilityClass;
import ru.kshnykin.kg.qa.education.api.testapp.api.dto.Category;
import ru.kshnykin.kg.qa.education.api.testapp.api.dto.Pet;
import ru.kshnykin.kg.qa.education.api.testapp.api.dto.Tag;

import java.util.List;

@UtilityClass
public class PetReqGenerator {

    public static Pet genPet() {
        return new Pet()
                .setId(0L)
                .setCategory(
                        new Category().setId(0L).setName("string")
                )
                .setName("doggie")
                .setPhotoUrls(List.of("string"))
                .setTags(
                        List.of(new Tag().setId(0L).setName("string"))
                )
                .setStatus(Pet.PetStatus.AVAILABLE);
    }

}