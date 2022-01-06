package ru.kshnykin.kg.qa.education.api.testapp.junit.console.launcher.commands;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import ru.kshnykin.kg.qa.education.api.testapp.junit.api.annotations.tags.Tags;

@SuppressWarnings("all")
@Parameters(commandNames = Commands.PET_COMMAND, commandDescription = "Pet controller tests")
public class PetCommand extends CommandBase {

    @Parameter(names = {"-a", "--add"}, description = "Add pet endpoint tests")
    private boolean addPet = false;

    @Parameter(names = {"-ui", "--upload-image"}, description = "Upload Image endpoint tests")
    private boolean uploadImage = false;

    @Override
    public String getControllerTag() {
        return Tags.PET;
    }

    @Override
    public void initServices() {
        this.services.put(Tags.PET_ADD, addPet);
        this.services.put(Tags.PET_UPLOAD_IMAGE, uploadImage);
    }

}