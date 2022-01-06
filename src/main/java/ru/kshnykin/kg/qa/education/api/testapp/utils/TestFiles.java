package ru.kshnykin.kg.qa.education.api.testapp.utils;

public enum TestFiles {

    GIF("tests/gif.gif", ".gif"),
    JPG("tests/jpg.jpg", ".jpg"),
    PNG("tests/png.png", ".png"),
    PDF("tests/pdf.pdf", ".pdf"),
    TXT("tests/txt.txt", ".txt");

    private final String path;
    private final String extension;

    TestFiles(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }

    public String getPath() {
        return path;
    }

    public String getExtension() {
        return extension;
    }

}