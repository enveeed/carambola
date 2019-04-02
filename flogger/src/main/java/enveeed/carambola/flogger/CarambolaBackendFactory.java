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

import com.google.common.flogger.backend.LoggerBackend;
import com.google.common.flogger.backend.system.BackendFactory;

import javax.annotation.Nonnull;

@SuppressWarnings("unused")
public final class CarambolaBackendFactory extends BackendFactory {
    private CarambolaBackendFactory() {}

    private static final CarambolaBackendFactory factory = new CarambolaBackendFactory();

    // ===

    @Override
    public LoggerBackend create(String loggingClassName) {
        return new CarambolaBackend(loggingClassName);
    }

    // ===

    /**
     * Getter method for {@link com.google.common.flogger.backend.system.DefaultPlatform}.
     * @return the backend factory.
     */
    @Nonnull
    @SuppressWarnings("unused")
    public static CarambolaBackendFactory get() {
        return factory;
    }
}
