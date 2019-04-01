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

import enveeed.carambola.dsl.carambola
import java.util.logging.Level

// This is an example "carambola.kts" configuration script file.
carambola {

    // Adapters are configured in the "adapters" block.
    adapters {

        // useFlogger() // for carambola-flogger
        // useSLF4J() // for carambola-slf4j
    }

    // Handlers are configured in the "handlers" block.
    handlers {

        // Handlers are added by calling handler(...)
        handler({

            // This is a lambda which acts as a HandlerExecutor, printing the statement to STDOUT
            System.out.println(this.content)

        }) {

            // This is the handler configuration block.

            // Filters are added by calling filter(...)
            filter {
                // This is a lambda which acts as a Filter, filtering all statements which have level below 100.
                this.level >= 100
            }

        }
    }

    // general minimum level, filtered out before it reaches any handler.
    // e.g. useful for pre-filtering APIs like Google Flogger as it provides a speed benefit
    level(Level.INFO)

}