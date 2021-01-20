package com.backend.loginandregistration.properties.module;

import java.io.IOException;
import java.util.Properties;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class PropertiesModule extends AbstractModule {
    @Override
    public void configure() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("localization.properties"));
            Names.bindProperties(binder(), props);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
