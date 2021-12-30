package ru.kshnykin.kg.qa.education.api.testapp.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.SystemUtils.USER_DIR;
import static ru.kshnykin.kg.qa.education.api.testapp.utils.Utils.execute;
import static ru.kshnykin.kg.qa.education.api.testapp.utils.Utils.supply;


public class IOHelper {

    public static final Charset UTF_8 = StandardCharsets.UTF_8;
    public static final String LINE_SEP = System.lineSeparator();

    public static String readFileAsString(File file) {
        List<String> stringList = readFileAsStringList(file);
        return String.join(LINE_SEP, stringList);
    }

    public static <T> T readYmlFileAsObject(String filePath, Class<T> tClass) {
        File file = getResourceAsFile(filePath);
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return Utils.supply(() -> objectMapper.readValue(file, tClass));
    }

    public static List<String> readFileAsStringList(File file) {
        return supply(() -> {
            FileInputStream fis = new FileInputStream(file);
            return IOUtils.readLines(fis, UTF_8);
        });
    }

    public static File getResourceAsFile(String filePath) {
        URL resource = getResource(filePath);
        return new File(resource.getPath());
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

    public static URL getResource(String filePath) {
        Objects.requireNonNull(filePath, "Resource file name can not be null");
        return getClassLoader().getResource(filePath);
    }

    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public static File getJavaFileBasePackage(Class<?> testClass) {
        final File testClassDir = new File(testClass.getProtectionDomain().getCodeSource().getLocation().getPath());
        if ("test-classes".equals(testClassDir.getName())) {
            return new File(USER_DIR + "/src/test/java/");
        } else {
            return new File(USER_DIR + "/src/main/java/");
        }
    }

    public static File getJavaClassAsFile(Class<?> testClass) {
        Objects.requireNonNull(testClass, "Class can not be null");
        String basePackageAndName = testClass.getTypeName().replace(".", "/") + ".java";
        File basePath = getJavaFileBasePackage(testClass);
        return new File(basePath, basePackageAndName);
    }

    public static void write(String text, File file) {
        Objects.requireNonNull(file, "File can not be null");
        execute(() -> IOUtils.write(text, new FileOutputStream(file), UTF_8));
    }

    public static long getFileSize(File file) {
        Objects.requireNonNull(file, "File can not be null");
        return Utils.supply(() -> Files.size(file.toPath()));
    }

    public static String getFileExtension(File file) {
        Objects.requireNonNull(file, "File can not be null");
        String fileName = file.getName();
        return FilenameUtils.getExtension(fileName);
    }

    public static String getFileContentType(File file) {
        Objects.requireNonNull(file, "File can not be null");
        return Utils.supply(() -> Files.probeContentType(file.toPath()));
    }

}