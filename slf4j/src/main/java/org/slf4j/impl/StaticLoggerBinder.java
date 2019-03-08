/*
 * Copyright (c) 2019 Arthur Schüler / enveeed (https://github.com/enveeed)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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
