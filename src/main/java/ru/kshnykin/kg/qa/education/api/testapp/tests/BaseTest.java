package ru.kshnykin.kg.qa.education.api.testapp.tests;

import ru.kshnykin.kg.qa.education.api.testapp.utils.IOHelper;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ru.kshnykin.kg.qa.education.api.testapp.utils.IOHelper.LINE_SEP;

public abstract class BaseTest {

    public String toStr(Object o) {
        return String.valueOf(o);
    }

    public Object replaceAfterDotIfDouble(Object number) {
        if (number instanceof Double) {
            String num = number.toString();
            return Integer.valueOf(num.substring(0, num.lastIndexOf(".")));
        }
        return number;
    }

    public <T> List<T> genList(Supplier<T> supplier, int count) {
        return IntStream
                .rangeClosed(1, count)
                .mapToObj(i -> supplier.get())
                .collect(Collectors.toList());
    }

    public void createTestsByCheckList(Object aClass) {
        createTestsByCheckList(aClass.getClass());
    }

    public void createTestsByCheckList(Class<?> testClass) {
        List<String> checkList = IOHelper.getResourceAsStringList("check-list.csv");
        File javaFile = IOHelper.getJavaClassAsFile(testClass);
        String javaFileBody = IOHelper.readFileAsString(javaFile);
        List<String> testCasesForAdd = getTestCasesForAdd(checkList, javaFileBody);
        if (!testCasesForAdd.isEmpty()) {
            String result = getFinalFile(javaFileBody, testCasesForAdd);
            IOHelper.write(result, javaFile);
        } else {
            //TODO: log
            System.out.println("\"There are no tests to create\"");
        }
    }

    private String getFinalFile(String javaFileBody, List<String> testCasesForAdd) {
        long time = new Date().getTime();
        StringJoiner result = new StringJoiner(LINE_SEP);
        for (String bodyString : javaFileBody.split(LINE_SEP)) {
            if (bodyString.equals("}")) {
                for (String testCase : testCasesForAdd) {
                    time++;
                    result.add("    @Test(title = \"" + testCase + "\")");
                    result.add("    public void test" + time + "() {");
                    result.add("        throw new UnimplementedTest();");
                    result.add("    }" + LINE_SEP);
                }
                result.add("}");
            } else {
                result.add(bodyString);
            }
        }
        return result.toString();
    }

    private List<String> getTestCasesForAdd(List<String> checkList, String javaFileBody) {
        return checkList.stream()
                .filter(testCase -> !javaFileBody.contains(testCase))
                .collect(Collectors.toList());
    }

}