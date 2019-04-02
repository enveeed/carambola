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

package enveeed.carambola.flogger;

import enveeed.carambola.Adapter;

import java.util.Properties;

/**
 * {@link Adapter} for Google Flogger.
 */
public final class FloggerAdapter implements Adapter {
    public FloggerAdapter() {}

    private final static String BACKEND_FACTORY_CLASS = "enveeed.carambola.flogger.CarambolaBackendFactory#get";

    // ===

    @Override
    public void initialize() {

        Properties properties = System.getProperties();

        // set the backend factory
        properties.setProperty("flogger.backend_factory", BACKEND_FACTORY_CLASS);

        // initialization completed.
    }
}
