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

import enveeed.carambola.script.CarambolaScriptEngineFactory;
import org.junit.jupiter.api.Test;

import javax.script.ScriptEngine;

import static org.junit.jupiter.api.Assertions.*;

class CarambolaTest {

    @Test
    void test() {

        ScriptEngine engine = assertDoesNotThrow(() -> new CarambolaScriptEngineFactory()
                .getScriptEngine());

        assertNotNull(engine);
    }
}