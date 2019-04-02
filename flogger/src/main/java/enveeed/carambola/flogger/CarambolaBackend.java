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
import enveeed.carambola.CarambolaApi;

import java.util.logging.Level;

public final class CarambolaBackend extends LoggerBackend {

    private final CarambolaApi api = Carambola.get();

    private final String name;

    // ===

    CarambolaBackend(String name) {
        this.name = name;
    }

    // ===

    @Override
    public String getLoggerName() {
        return this.name;
    }

    @Override
    public boolean isLoggable(Level lvl) {
        return lvl.intValue() >= api.level();
    }

    @Override
    public void log(LogData data) {
        this.api
                .log(FloggerStatement.of(data));
    }

    @Override
    public void handleError(RuntimeException error, LogData badData) {
        System.err.println(error + " - data:" + badData);
        error.printStackTrace(System.err);
        // TODO ERR
    }

}
