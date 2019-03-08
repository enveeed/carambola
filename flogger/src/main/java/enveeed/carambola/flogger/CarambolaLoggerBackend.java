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

import com.google.common.flogger.backend.LogData;
import com.google.common.flogger.backend.LoggerBackend;
import enveeed.carambola.Carambola;

import java.util.logging.Level;

public final class CarambolaLoggerBackend extends LoggerBackend {

    private final Carambola carambola = Carambola.get();

    private final String name;

    // ===

    CarambolaLoggerBackend(String name) {
        this.name = name;
    }

    // ===

    @Override
    public String getLoggerName() {
        return this.name;
    }

    @Override
    public boolean isLoggable(Level lvl) {
        return true;
    }

    @Override
    public void log(LogData data) {
        System.out.println("CARAMBOLAAAA "+data.toString());
    }

    @Override
    public void handleError(RuntimeException error, LogData badData) {

    }

}
