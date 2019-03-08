package org.slf4j.impl;

import enveeed.carambola.slf4j.CarambolaLoggerFactory;
import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

public final class StaticLoggerBinder implements LoggerFactoryBinder {

    private static final StaticLoggerBinder	SINGLETON = new StaticLoggerBinder();
    public static final String REQUESTED_API_VERSION = "1.7.0";

    // ===

    public static StaticLoggerBinder getSingleton() {
        return SINGLETON;
    }

    // ===

    @Override
    public ILoggerFactory getLoggerFactory() {
        return new CarambolaLoggerFactory();
    }

    @Override
    public String getLoggerFactoryClassStr() {
        return getLoggerFactory().getClass().getName();
    }

    // ===
}
