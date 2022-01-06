package ru.kshnykin.kg.qa.education.api.testapp.junit.console.launcher.converters;

import com.beust.jcommander.IStringConverter;
import ru.kshnykin.kg.qa.education.api.testapp.config.Environments;

public class EnvConverter implements IStringConverter<Environments> {

    @Override
    public Environments convert(String env) {
        return Environments.parse(env);
    }

}