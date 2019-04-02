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

import enveeed.carambola.Carambola;
import org.slf4j.event.Level;
import org.slf4j.helpers.MarkerIgnoringBase;

/**
 * A SLF4J logger which redirects everything to Carambola.get().
 */
public final class CarambolaSLF4JLogger extends MarkerIgnoringBase {

    private final String name;

    // ===

    CarambolaSLF4JLogger(String name) {
        this.name = name;
    }

    // ===

    @Override
    public boolean isTraceEnabled() {
        return SLF4JStatement.fromSLF4JLevel(Level.TRACE) >= Carambola.get().level();
    }

    @Override
    public void trace(String msg) {
        if(isTraceEnabled()) Carambola.get().log(SLF4JStatement.ofLiteral(Level.TRACE, msg, null));
    }

    @Override
    public void trace(String format, Object arg) {
        if(isTraceEnabled()) Carambola.get().log(SLF4JStatement.ofArguments(Level.TRACE, format, null, arg));
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        if(isTraceEnabled()) Carambola.get().log(SLF4JStatement.ofArguments(Level.TRACE, format, null, arg1, arg2));
    }

    @Override
    public void trace(String format, Object... arguments) {
        if(isTraceEnabled()) Carambola.get().log(SLF4JStatement.ofArguments(Level.TRACE, format, null, arguments));
    }

    @Override
    public void trace(String msg, Throwable t) {
        if(isTraceEnabled()) Carambola.get().log(SLF4JStatement.ofLiteral(Level.TRACE, msg, t));
    }

    @Override
    public boolean isDebugEnabled() {
        return SLF4JStatement.fromSLF4JLevel(Level.DEBUG) >= Carambola.get().level();
    }

    @Override
    public void debug(String msg) {
        if(isDebugEnabled()) Carambola.get().log(SLF4JStatement.ofLiteral(Level.DEBUG, msg, null));
    }

    @Override
    public void debug(String format, Object arg) {
        if(isDebugEnabled()) Carambola.get().log(SLF4JStatement.ofArguments(Level.DEBUG, format, null, arg));
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        if(isDebugEnabled()) Carambola.get().log(SLF4JStatement.ofArguments(Level.DEBUG, format, null, arg1, arg2));
    }

    @Override
    public void debug(String format, Object... arguments) {
        if(isDebugEnabled()) Carambola.get().log(SLF4JStatement.ofArguments(Level.DEBUG, format, null, arguments));
    }

    @Override
    public void debug(String msg, Throwable t) {
        if(isDebugEnabled()) Carambola.get().log(SLF4JStatement.ofLiteral(Level.DEBUG, msg, t));
    }

    @Override
    public boolean isInfoEnabled() {
        return SLF4JStatement.fromSLF4JLevel(Level.INFO) >= Carambola.get().level();
    }

    @Override
    public void info(String msg) {
        if(isInfoEnabled()) Carambola.get().log(SLF4JStatement.ofLiteral(Level.INFO, msg, null));
    }

    @Override
    public void info(String format, Object arg) {
        if(isInfoEnabled()) Carambola.get().log(SLF4JStatement.ofArguments(Level.INFO, format, null, arg));
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        if(isInfoEnabled()) Carambola.get().log(SLF4JStatement.ofArguments(Level.INFO, format, null, arg1, arg2));
    }

    @Override
    public void info(String format, Object... arguments) {
        if(isInfoEnabled()) Carambola.get().log(SLF4JStatement.ofArguments(Level.INFO, format, null, arguments));
    }

    @Override
    public void info(String msg, Throwable t) {
        if(isInfoEnabled()) Carambola.get().log(SLF4JStatement.ofLiteral(Level.INFO, msg, t));
    }

    @Override
    public boolean isWarnEnabled() {
        return SLF4JStatement.fromSLF4JLevel(Level.WARN) >= Carambola.get().level();
    }

    @Override
    public void warn(String msg) {
        if(isWarnEnabled()) Carambola.get().log(SLF4JStatement.ofLiteral(Level.WARN, msg, null));
    }

    @Override
    public void warn(String format, Object arg) {
        if(isWarnEnabled()) Carambola.get().log(SLF4JStatement.ofArguments(Level.WARN, format, null, arg));
    }

    @Override
    public void warn(String format, Object... arguments) {
        if(isWarnEnabled()) Carambola.get().log(SLF4JStatement.ofArguments(Level.WARN, format, null, arguments));
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        if(isWarnEnabled()) Carambola.get().log(SLF4JStatement.ofArguments(Level.WARN, format, null, arg1, arg2));
    }

    @Override
    public void warn(String msg, Throwable t) {
        if(isWarnEnabled()) Carambola.get().log(SLF4JStatement.ofLiteral(Level.WARN, msg, t));
    }

    @Override
    public boolean isErrorEnabled() {
        return SLF4JStatement.fromSLF4JLevel(Level.ERROR) >= Carambola.get().level();
    }

    @Override
    public void error(String msg) {
        if(isErrorEnabled()) Carambola.get().log(SLF4JStatement.ofLiteral(Level.ERROR, msg, null));
    }

    @Override
    public void error(String format, Object arg) {
        if(isErrorEnabled()) Carambola.get().log(SLF4JStatement.ofArguments(Level.ERROR, format, null, arg));
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        if(isErrorEnabled()) Carambola.get().log(SLF4JStatement.ofArguments(Level.ERROR, format, null, arg1, arg2));
    }

    @Override
    public void error(String format, Object... arguments) {
        if(isErrorEnabled()) Carambola.get().log(SLF4JStatement.ofArguments(Level.ERROR, format, null, arguments));
    }

    @Override
    public void error(String msg, Throwable t) {
        if(isErrorEnabled()) Carambola.get().log(SLF4JStatement.ofLiteral(Level.ERROR, msg, t));
    }
}
