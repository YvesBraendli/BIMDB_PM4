package com.debugdemons.bimdb.utils;

import org.junit.jupiter.api.Test;

public class LoggerProviderTest implements LoggerProvider {

    @Test
    void loggerProviderTest() {
        getLogger().info("Test");
    }
}
