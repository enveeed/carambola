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

import enveeed.carambola.Handler;
import enveeed.carambola.Statement;

import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

public final class StandardHandler implements Handler {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final String RED = "\u001B[48;2;201;54;54m";
    private static final String YELLOW = "\u001B[48;2;248;106;28m";
    private static final String BLUE = "\u001B[48;2;0;91;255m";

    @Override
    public void handle(Statement log) {

        String levelColor;
        String levelStr;

        int level = log.getLevel();

        if(level >= Level.SEVERE.intValue()) {
            levelColor = RED;
            levelStr = "E";
        } else if(level >= Level.WARNING.intValue()) {
            levelColor = YELLOW;
            levelStr = "W";
        } else if(level >= Level.INFO.intValue()) {
            levelColor = "";
            levelStr = "I";
        } else {
            levelColor = BLUE;
            levelStr = "D";
        }

        //

        String out = String.format("\u001B[48;2;158;158;158m\u001B[30m%s \u001B[0m\u001B[37m%s %s \u001B[0m %s",
                formatter.format(log.getTimestamp()),
                levelColor, levelStr,
                log.getContent());

        //

        System.out.println(out);

        if(log.getCause().isPresent()) log.getCause().orElseThrow().printStackTrace(System.err);
    }

}
