package com.debugdemons.bimdb.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * LoggerProvider is a feature interface that provides Logging capability for anyone
 * implementing it where logger needs to operate in serializable environment
 * without being static.
 */
public interface LoggerProvider {
    default Logger getLogger() {
        return LoggerFactory.getLogger(getClass());
    }
}

