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

package enveeed.carambola.jul;

import enveeed.carambola.Adapter;

import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * An {@link enveeed.carambola.Adapter} for JUL.
 */
public final class JULAdapter implements Adapter {

    @Override
    public void initialize() {

        Logger root = getRootLogger();

        // remove standard handlers
        for (Handler handler : root.getHandlers()) root.removeHandler(handler);

        // add the redirecting handler
        CarambolaJULHandler julHandler = new CarambolaJULHandler();
        root.addHandler(julHandler);
    }

    // ===

    private static Logger getRootLogger() {
        return LogManager.getLogManager().getLogger("");
    }
}
