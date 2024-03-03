package com.atf.template.ui.cucumber.config;

public enum PropertiesKeys {
    BASE_URL("baseURL"),
    HEADLESS("headless");
    private final String propertyKey;

    PropertiesKeys(String propertyKey) {
        this.propertyKey = propertyKey;
    }
    public String getPropertyKey() {
        return propertyKey;
    }
}
