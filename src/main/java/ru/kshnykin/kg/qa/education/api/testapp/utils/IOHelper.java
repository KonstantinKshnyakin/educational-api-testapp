package ru.kshnykin.kg.qa.education.api.testapp.utils;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.kshnykin.kg.qa.education.api.testapp.utils.Utils.execute;
import static ru.kshnykin.kg.qa.education.api.testapp.utils.Utils.supply;


public class IOHelper {

    public static final Charset UTF_8 = StandardCharsets.UTF_8;
    public static final String LINE_SEP = System.lineSeparator();

    public static String readFileAsString(File file) {
        List<String> stringList = readFileAsStringList(file);
        return String.join(LINE_SEP, stringList);
    }

    public static List<String> readFileAsStringList(File file) {
        return supply(() -> {
            FileInputStream fis = new FileInputStream(file);
            return IOUtils.readLines(fis, UTF_8);
        });
    }

    public static List<String> getResourceAsStringList(final String filePath) {
        InputStream resourceAsStream = getResourceAsStream(filePath);
        return new BufferedReader(new InputStreamReader(resourceAsStream, UTF_8))
                .lines()
                .collect(Collectors.toList());
    }

    public static InputStream getResourceAsStream(final String filePath) {
        Objects.requireNonNull(filePath, "Resource file name can not be null");
        return getClassLoader().getResourceAsStream(filePath);
    }

    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public static File getJavaFileBasePackage(Class<?> testClass) {
        final File testClassDir = new File(testClass.getProtectionDomain().getCodeSource().getLocation().getPath());
        String userDir = System.getProperty("user.dir");
        if ("test-classes".equals(testClassDir.getName())) {
            return new File(userDir + "/src/test/java/");
        } else {
            return new File(userDir + "/src/main/java/");
        }
    }

    public static File getJavaClassAsFile(Class<?> testClass) {
        Objects.requireNonNull(testClass, "Class can not be null");
        String basePackageAndName = testClass.getTypeName().replace(".", "/") + ".java";
        File basePath = getJavaFileBasePackage(testClass);
        return new File(basePath, basePackageAndName);
    }

    public static void write(String text, File file) {
        execute(() -> IOUtils.write(text, new FileOutputStream(file), UTF_8));
    }

}