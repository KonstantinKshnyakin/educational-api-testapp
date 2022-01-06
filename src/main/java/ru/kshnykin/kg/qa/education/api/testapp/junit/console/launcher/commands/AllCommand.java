package ru.kshnykin.kg.qa.education.api.testapp.junit.console.launcher.commands;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import static ru.kshnykin.kg.qa.education.api.testapp.junit.api.annotations.tags.Tags.ALL;
import static ru.kshnykin.kg.qa.education.api.testapp.junit.console.launcher.commands.Commands.ALL_COMMAND;

@SuppressWarnings("all")
@Parameters(commandNames = ALL_COMMAND, commandDescription = "Run all project")
public class AllCommand extends CommandBase {

    @Parameter(names = {"-r", "--run"})
    private boolean isRunALl = false;

    @Override
    protected String getControllerTag() {
        return ALL;
    }

    @Override
    protected void initServices() {
    }

}