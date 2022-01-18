package ru.kshnykin.kg.qa.education.api.testapp.junit.console.launcher;

import com.beust.jcommander.Parameter;
import ru.kshnykin.kg.qa.education.api.testapp.config.Environments;
import ru.kshnykin.kg.qa.education.api.testapp.junit.console.launcher.converters.EnvConverter;

@SuppressWarnings("unused")
public class MainOptions {

    private static boolean help = false;
    private static boolean enableAnsiColor = false;
    private static Environments environment = Environments.PROD;


    @Parameter(names = {"-h", "--help"}, help = true, description = "Print usage.")
    public void setHelp(boolean help) {
        MainOptions.help = help;
    }

    @Parameter(names = {"-eac", "--enable-ansi-color"}, description = "Enable ANSI colors in output (not supported by all terminals).")
    public void setDisableAnsiColors(boolean disableAnsiColors) {
        MainOptions.enableAnsiColor = disableAnsiColors;
    }

    @Parameter(names = {"-e", "--environment"}, description = "Test environment.", converter = EnvConverter.class)
    public void setEnvironment(Environments environment) {
        MainOptions.environment = environment;
    }

    public boolean isHelp() {
        return help;
    }

    public boolean isEnableAnsiColor() {
        return enableAnsiColor;
    }

    public Environments getEnvironment() {
        return environment;
    }

}