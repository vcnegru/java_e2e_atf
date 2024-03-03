package com.atf.template.ui.cucumber.context;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private static final Map<DataKeys, Object> scenarioContext = new HashMap<>();
    public static void saveToContext(DataKeys key, Object value) {
        scenarioContext.put(key, value);
    }
    public static <T> T getFromContext(DataKeys key) {
        return (T) scenarioContext.get(key);
    }
    public static void resetContext() {
        scenarioContext.clear();
    }

}
