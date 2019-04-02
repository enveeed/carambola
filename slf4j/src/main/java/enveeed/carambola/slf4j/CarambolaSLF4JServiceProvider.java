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

package enveeed.carambola.slf4j;

import org.slf4j.ILoggerFactory;
import org.slf4j.IMarkerFactory;
import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.helpers.NOPMDCAdapter;
import org.slf4j.spi.MDCAdapter;
import org.slf4j.spi.SLF4JServiceProvider;

/**
 * {@link SLF4JServiceProvider} implementation for carambola, using META-INF/services.
 */
public final class CarambolaSLF4JServiceProvider implements SLF4JServiceProvider {

    private final CarambolaLoggerFactory factory = new CarambolaLoggerFactory();

    // ===

    @Override
    public ILoggerFactory getLoggerFactory() {
        return this.factory;
    }

    @Override
    public IMarkerFactory getMarkerFactory() {
        return new BasicMarkerFactory(); // not supported.
    }

    @Override
    public MDCAdapter getMDCAdapter() {
        return new NOPMDCAdapter(); // not supported.
    }

    @Override
    public String getRequesteApiVersion() {
        return "1.8.0-beta4";
    }


    @Override
    public void initialize() {
        // NO-OP
    }

    // ===

    /**
     * Create the CarambolaSLF4JServiceProvider service (called by the ServiceLoader)
     * @return a new CarambolaSLF4JServiceProvider
     */
    @SuppressWarnings("unused")
    public static CarambolaSLF4JServiceProvider provider() {
        return new CarambolaSLF4JServiceProvider();
    }
}
