package com.vigatec.utils;

import java.util.Properties;

import com.vigatec.core.enums.EnvType;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() {
        String environment = System.getProperty("environment", String.valueOf(EnvType.QA));

        switch (EnvType.valueOf(environment)) {
            case QA -> properties = PropertiesUtils.propertyLoader("src/main/resources/config/QA.properties");
            default -> throw new IllegalStateException("invalid scenario type: " + environment);
        }
    }

    public static ConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    private String getPropertyOrThrow(String propertyName) {
        String prop = properties.getProperty(propertyName);
        if (prop != null)
            return prop;
        else
            throw new RuntimeException("Property '" + propertyName + "' is not specified in the properties file");
    }

    public String getBaseUrl() {
        return getPropertyOrThrow("url.base");
    }

    public String getUrlCompany() {
        return getPropertyOrThrow("url.company");
    }

    public Long getExplicitWait() {
        return Long.parseLong(getPropertyOrThrow("explicit.wait"));
    }

}
