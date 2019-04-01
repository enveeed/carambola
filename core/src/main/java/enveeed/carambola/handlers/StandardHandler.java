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

package enveeed.carambola.handlers;

import enveeed.carambola.HandlerExecutor;
import enveeed.carambola.Statement;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

public final class StandardHandler implements HandlerExecutor {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            .withZone(ZoneId.systemDefault());

    private static final String ERROR   = "\u001B[48;2;201;54;54m ERROR \u001B[0m";
    private static final String TRACE   = "\u001B[48;2;201;54;54m ERROR \u001B[0m";
    private static final String WARN    = "\u001B[48;2;248;106;28m WARN  \u001B[0m";
    private static final String INFO   = " INFO  ";
    private static final String DEBUG   = "\u001B[48;2;0;91;255m DEBUG \u001B[0m";

    @Override
    public void handle(Statement log) {

        String prefix;

        int level = log.getLevel();

        if(level >= Level.SEVERE.intValue()) {
            prefix = ERROR;
        } else if(level >= Level.WARNING.intValue()) {
            prefix = WARN;
        } else if(level >= Level.INFO.intValue()) {
            prefix = INFO;
        } else {
            prefix = DEBUG;
        }

        //

        String out = String.format("%s %s | %s",
                prefix,
                formatter.format(log.getTimestamp()),
                log.getContent());

        System.out.println(out);

        if(log.getCause().isPresent()) {
            log.getCause().orElseThrow().printStackTrace(System.out);
        }
    }

}
