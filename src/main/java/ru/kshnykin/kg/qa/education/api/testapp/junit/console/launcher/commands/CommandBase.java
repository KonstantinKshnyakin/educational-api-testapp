package ru.kshnykin.kg.qa.education.api.testapp.junit.console.launcher.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public abstract class CommandBase implements ICommand {

    protected Map<String, Boolean> services = new HashMap<>();

    protected abstract String getControllerTag();

    protected abstract void initServices();

    protected boolean isAllServiceDisabled() {
        return !services.containsValue(true);
    }

    @Override
    public String getTagExpression() {
        initServices();
        StringJoiner tagsExpression = new StringJoiner(" & ", "(", ")");
        tagsExpression.add(getControllerTag());
        if (!services.isEmpty()) {
            StringJoiner serviceStringJoiner = new StringJoiner(" | ", "(", ")");
            if (isAllServiceDisabled()) {
                services.keySet().forEach(serviceStringJoiner::add);
            } else {
                services.entrySet().stream()
                        .filter(Map.Entry::getValue)
                        .forEach(e -> serviceStringJoiner.add(e.getKey()));
            }
            tagsExpression.add(serviceStringJoiner.toString());
        }
        return tagsExpression.toString();
    }

}