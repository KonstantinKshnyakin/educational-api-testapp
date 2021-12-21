package ru.kshnykin.kg.qa.education.api.testapp.utils;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static ru.kshnykin.kg.qa.education.api.testapp.utils.IOHelper.LINE_SEP;

public class TestHelper {

    public static void createTestsByCheckList(Class<?> testClass) {
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

    private static String getFinalFile(String javaFileBody, List<String> testCasesForAdd) {
        long time = new Date().getTime();
        StringJoiner result = new StringJoiner(LINE_SEP);
        for (String bodyString : javaFileBody.split(LINE_SEP)) {
            if (bodyString.equals("}")) {
                for (String testCase : testCasesForAdd) {
                    time++;
                    result.add("    @Test");
                    result.add("    @DisplayName(\"" + testCase + "\")");
                    result.add("    @Disabled");
                    result.add("    public void test" + time + "() {");
                    result.add("        throw new TestSkippedException(\"Unimplemented test\");");
                    result.add("    }" + LINE_SEP);
                }
                result.add("}");
            } else {
                result.add(bodyString);
            }
        }
        return result.toString();
    }

    private static List<String> getTestCasesForAdd(List<String> checkList, String javaFileBody) {
        return checkList.stream()
                .filter(testCase -> !javaFileBody.contains(testCase))
                .collect(Collectors.toList());
    }

}