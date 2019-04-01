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

package enveeed.carambola.filters;

import enveeed.carambola.Filter;
import enveeed.carambola.Statement;

import java.util.logging.Level;

public final class LevelFilter implements Filter {

    private final int value;

    // ===

    public LevelFilter(int value) {
        this.value = value;
    }

    public LevelFilter(Level level) {
        this.value = level.intValue();
    }

    // ===

    @Override
    public boolean test(Statement statement) {
        return statement.getLevel() >= this.value;
    }

    // ===

    public int getValue() {
        return this.value;
    }
}
