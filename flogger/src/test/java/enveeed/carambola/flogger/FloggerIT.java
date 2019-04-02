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

import com.google.common.flogger.FluentLogger;
import enveeed.carambola.Carambola;
import enveeed.carambola.HandlerExecutor;
import enveeed.carambola.Statement;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public final class FloggerIT {

    public static final TestHandler handler = new TestHandler();

    // ===

    @BeforeAll
    static void before() {
        Carambola.init();
    }

    @Test
    @Order(0)
    void testInit() {

        assertNotNull(System.getProperty("flogger.backend_factory"));

    }

    @Test
    @Order(1)
    void testAdapter() {

        assertNull(handler.statement);

        String test = "This is a test message.";

        FluentLogger.forEnclosingClass().atInfo().log(test);

        assertNotNull(handler.statement);
        assertEquals(handler.statement.getContent(), test);
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
