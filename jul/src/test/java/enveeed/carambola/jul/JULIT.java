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

import enveeed.carambola.Carambola;
import enveeed.carambola.HandlerExecutor;
import enveeed.carambola.Statement;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public final class JULIT {

    public static final TestHandler handler = new TestHandler();

    // ===

    @BeforeAll
    static void before() {
        Carambola.init();
    }

    @Test
    @Order(0)
    void testInit() {

        Logger root = Logger.getLogger("");

        assertNotNull(root);

        Handler[] handlers = root.getHandlers();

        assertNotNull(handlers);

        assertEquals(handlers.length, 1);

        Handler handler = handlers[0];

        assertEquals(CarambolaJULHandler.class, handler.getClass());
    }

    @Test
    @Order(1)
    void testAdapter() {

        assertNull(handler.statement);

        String test = "This is a test message.";

        Logger.getLogger("enveeed.carambola.jul.JULIT").log(Level.INFO, test);

        assertNotNull(handler.statement);
        assertEquals(test, handler.statement.getContent());
    }

    // ===

    public static class TestHandler implements HandlerExecutor {

        private Statement statement = null;

        @Override
        public void handle(Statement statement) {
            this.statement = statement;
        }
    }

}
