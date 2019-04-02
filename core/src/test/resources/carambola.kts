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

import enveeed.carambola.dsl.*
import enveeed.carambola.handlers.StandardHandler
import java.util.logging.Level

// carambola configuration script for TESTING ONLY
carambola {

    handlers {
        handler(StandardHandler())
        handler({
            System.err.println(this)
        }) {
            filter {
                this.level >= Level.SEVERE.intValue()
            }
        }
    }

    level(Level.FINE)

}
