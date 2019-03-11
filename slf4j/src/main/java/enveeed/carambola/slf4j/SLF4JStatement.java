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

package enveeed.carambola.slf4j;

import enveeed.carambola.Statement;
import org.slf4j.event.Level;
import org.slf4j.helpers.MessageFormatter;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

public final class SLF4JStatement implements Statement {

    private final Instant timestamp;

    private final Level level;

    private final String format;
    private final Object literal;
    private final Object[] arguments;

    private final Throwable cause;

    // ===

    private SLF4JStatement(Instant timestamp, Level level, String format, Object literal, Throwable cause, Object[] arguments) {
        this.timestamp = timestamp;
        this.level = level;
        this.format = format;
        this.literal = literal;
        this.arguments = arguments;
        this.cause = cause;
    }

    // ===

    @Override
    public Instant getTimestamp() {
        return this.timestamp;
    }

    @Override
    public String getContent() {
        if(this.literal != null) {
            return Objects.toString(this.literal);
        }
        else {
            return MessageFormatter.format(this.format, this.arguments).getMessage();
        }
    }

    @Override
    public int getLevel() {
        return fromSLF4JLevel(this.level);
    }

    // ===

    @Override
    public Optional<Throwable> getCause() {
        return Optional.ofNullable(this.cause);
    }

    // ===

    public static int fromSLF4JLevel(Level level) {
        switch (level) {
            case ERROR:     return 1000;
            case WARN:      return 900;
            case INFO:      return 800;
            case DEBUG:     return 100;
            case TRACE:     return 50;
            default: throw new IllegalArgumentException("Invalid SLF4J Level: " + level);
        }
    }

    // ===

    public static SLF4JStatement ofLiteral(Level level, Object literal, Throwable cause) {
        return new SLF4JStatement(Instant.now(), level, null, literal, cause, null);
    }

    public static SLF4JStatement ofArguments(Level level, String format, Throwable cause, Object... arguments) {
        return new SLF4JStatement(Instant.now(), level, format, null, cause, arguments);
    }
}
