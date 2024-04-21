package com.atf.template.ui.cucumber.context;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

@Slf4j
public class ScenarioContext {
    private static final Map<DataKeys, Object> scenarioContext = new HashMap<>();

    public static void saveToContext(DataKeys key, Object value) {
        scenarioContext.put(key, value);
        log.info("Put to context <key, value>: <{}, {}>", key, value);
    }
    public static <T> T getFromContext(DataKeys key) {
        return (T) scenarioContext.get(key);
    }
    public static void resetContext() {
        scenarioContext.clear();
    }

}
