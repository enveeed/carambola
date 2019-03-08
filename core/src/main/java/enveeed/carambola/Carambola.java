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

package enveeed.carambola;

public final class Carambola {

    private static Carambola carambola = null;

    // ===

    private Carambola() {}

    // ===

    public static Carambola get() {
        if(carambola == null) carambola = new Carambola();
        return carambola;
    }

    // ===

    private final HandlerRegistry handlers = new HandlerRegistry();

    // ===

    public void log(Statement statement) {}

    // ===

    public HandlerRegistry getHandlers() {
        return this.handlers;
    }
}
