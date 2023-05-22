package com.debugdemons.bimdb.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LoggerProviderTest implements LoggerProvider {

    @Test
    void loggerProviderTest() {
        assertDoesNotThrow(() -> getLogger().info("Test"));
    }
}
