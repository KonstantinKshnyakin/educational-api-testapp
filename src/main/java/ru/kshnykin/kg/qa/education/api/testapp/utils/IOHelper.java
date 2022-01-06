package ru.kshnykin.kg.qa.education.api.testapp.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.SystemUtils.USER_DIR;
import static ru.kshnykin.kg.qa.education.api.testapp.utils.Utils.execute;
import static ru.kshnykin.kg.qa.education.api.testapp.utils.Utils.supply;


@SuppressWarnings({"UnusedReturnValue", "unused"})
public class IOHelper {

    public static final Charset UTF_8 = StandardCharsets.UTF_8;
    public static final String LINE_SEP = System.lineSeparator();

    public static <T> T getYmlFileAsObject(String filePath, Class<T> tClass) {
        String resource = getResourceAsString(filePath);
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return Utils.supply(() -> objectMapper.readValue(resource, tClass));
    }

    public static String getFileAsString(File file) {
        List<String> stringList = getFileAsStringList(file);
        return String.join(LINE_SEP, stringList);
    }

    public static List<String> getFileAsStringList(File file) {
        return supply(() -> IOUtils.readLines(new InputStreamReader(new FileInputStream(file))));
    }

    public static File getResourceAsTempFile(TestFiles testFiles) {
        String resourceAsString = getResourceAsString(testFiles.getPath());
        File tempFile = createTempFile(testFiles.getExtension());
        write(resourceAsString, tempFile);
        return tempFile;
    }

    public static List<String> getResourceAsStringList(String filePath) {
        return getResourceAsBufferReader(filePath)
                .lines()
                .toList();
    }

    public static String getResourceAsString(String filePath) {
        return getResourceAsBufferReader(filePath)
                .lines()
                .collect(Collectors.joining(LINE_SEP));
    }

    public static BufferedReader getResourceAsBufferReader(String filePath) {
        Objects.requireNonNull(filePath, "Resource file name can not be null");
        InputStream resourceAsStream = getResourceAsStream(filePath);
        return new BufferedReader(new InputStreamReader(resourceAsStream, UTF_8));
    }

    public static InputStream getResourceAsStream(String filePath) {
        Objects.requireNonNull(filePath, "Resource file name can not be null");
        return getClassLoader().getResourceAsStream(filePath);
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

    public static File createTempFile(String extension) {
        return createTempFile("autotest", extension);
    }

    public static File createTempFile() {
        return createTempFile("autotest", ".tmp");
    }

    public static File createTempFile(String prefix, String extension) {
        String suffix = extension.startsWith(".") ? extension : "." + extension;
        File tempFile = Utils.supply(() -> File.createTempFile(prefix, suffix));
        tempFile.deleteOnExit();
        return tempFile;
    }

}