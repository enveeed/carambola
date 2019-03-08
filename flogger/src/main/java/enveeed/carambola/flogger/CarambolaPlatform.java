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
import com.google.common.flogger.backend.Platform;
import com.google.common.flogger.backend.system.StackBasedCallerFinder;

public final class CarambolaPlatform extends Platform {

    @Override
    protected LogCallerFinder getCallerFinderImpl() {
        return StackBasedCallerFinder.getInstance();
    }

    @Override
    protected LoggerBackend getBackendImpl(String className) {
        return new CarambolaLoggerBackend(className);
    }

    @Override
    protected String getConfigInfoImpl() {
        return "platform: " + "CarambolaPlatform" + "\n";
    }
}
