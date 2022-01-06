package ru.kshnykin.kg.qa.education.api.testapp;

import ru.kshnykin.kg.qa.education.api.testapp.junit.console.launcher.ConsoleLauncher;
import ru.kshnykin.kg.qa.education.api.testapp.junit.console.launcher.MainOptions;
import ru.kshnykin.kg.qa.education.api.testapp.junit.console.launcher.commands.AllCommand;
import ru.kshnykin.kg.qa.education.api.testapp.junit.console.launcher.commands.ICommand;
import ru.kshnykin.kg.qa.education.api.testapp.junit.console.launcher.commands.PetCommand;

public class Launcher {

    public static void main(String[] args) {
        ICommand[] commandList = new ICommand[]{
                new PetCommand(),
                new AllCommand()
        };
        new ConsoleLauncher(new MainOptions(), commandList).run(args);
    }

}