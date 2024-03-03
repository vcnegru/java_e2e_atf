package com.atf.template.ui.cucumber.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertyReader {
    private static final String fileName = "properties/application.properties";
    private static final String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream(rootPath + fileName));
        } catch (IOException e) {
            throw new RuntimeException("File does not exist: " + fileName);
        }
    }

    public static String getProperty(com.atf.template.ui.cucumber.config.PropertiesKeys propertiesKeys) {
        try {
            return properties.getProperty(propertiesKeys.getPropertyKey());
        } catch (NullPointerException exception) {
            throw new RuntimeException("Property not found: " + propertiesKeys.getPropertyKey());
        }
    }
}
