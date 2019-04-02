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
import enveeed.carambola.CarambolaApi;
import org.slf4j.event.Level;
import org.slf4j.helpers.MarkerIgnoringBase;

/**
 * A SLF4J logger which redirects everything to api.
 */
public final class CarambolaSLFJLogger extends MarkerIgnoringBase {

    private final CarambolaApi api = Carambola.get();
    private final String name;

    // ===

    CarambolaSLFJLogger(String name) {
        this.name = name;
    }

    // ===

    @Override
    public boolean isTraceEnabled() {
        return SLF4JStatement.fromSLF4JLevel(Level.TRACE) >= api.level();
    }

    @Override
    public void trace(String msg) {
        if(isTraceEnabled()) this.api.log(SLF4JStatement.ofLiteral(Level.TRACE, msg, null));
    }

    @Override
    public void trace(String format, Object arg) {
        if(isTraceEnabled()) this.api.log(SLF4JStatement.ofArguments(Level.TRACE, format, null, arg));
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        if(isTraceEnabled()) this.api.log(SLF4JStatement.ofArguments(Level.TRACE, format, null, arg1, arg2));
    }

    @Override
    public void trace(String format, Object... arguments) {
        if(isTraceEnabled()) this.api.log(SLF4JStatement.ofArguments(Level.TRACE, format, null, arguments));
    }

    @Override
    public void trace(String msg, Throwable t) {
        if(isTraceEnabled()) this.api.log(SLF4JStatement.ofLiteral(Level.TRACE, msg, t));
    }

    @Override
    public boolean isDebugEnabled() {
        return SLF4JStatement.fromSLF4JLevel(Level.DEBUG) >= api.level();
    }

    @Override
    public void debug(String msg) {
        if(isDebugEnabled()) this.api.log(SLF4JStatement.ofLiteral(Level.DEBUG, msg, null));
    }

    @Override
    public void debug(String format, Object arg) {
        if(isDebugEnabled()) this.api.log(SLF4JStatement.ofArguments(Level.DEBUG, format, null, arg));
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        if(isDebugEnabled()) this.api.log(SLF4JStatement.ofArguments(Level.DEBUG, format, null, arg1, arg2));
    }

    @Override
    public void debug(String format, Object... arguments) {
        if(isDebugEnabled()) this.api.log(SLF4JStatement.ofArguments(Level.DEBUG, format, null, arguments));
    }

    @Override
    public void debug(String msg, Throwable t) {
        if(isDebugEnabled()) this.api.log(SLF4JStatement.ofLiteral(Level.DEBUG, msg, t));
    }

    @Override
    public boolean isInfoEnabled() {
        return SLF4JStatement.fromSLF4JLevel(Level.INFO) >= api.level();
    }

    @Override
    public void info(String msg) {
        if(isInfoEnabled()) this.api.log(SLF4JStatement.ofLiteral(Level.INFO, msg, null));
    }

    @Override
    public void info(String format, Object arg) {
        if(isInfoEnabled()) this.api.log(SLF4JStatement.ofArguments(Level.INFO, format, null, arg));
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        if(isInfoEnabled()) this.api.log(SLF4JStatement.ofArguments(Level.INFO, format, null, arg1, arg2));
    }

    @Override
    public void info(String format, Object... arguments) {
        if(isInfoEnabled()) this.api.log(SLF4JStatement.ofArguments(Level.INFO, format, null, arguments));
    }

    @Override
    public void info(String msg, Throwable t) {
        if(isInfoEnabled()) this.api.log(SLF4JStatement.ofLiteral(Level.INFO, msg, t));
    }

    @Override
    public boolean isWarnEnabled() {
        return SLF4JStatement.fromSLF4JLevel(Level.WARN) >= api.level();
    }

    @Override
    public void warn(String msg) {
        if(isWarnEnabled()) this.api.log(SLF4JStatement.ofLiteral(Level.WARN, msg, null));
    }

    @Override
    public void warn(String format, Object arg) {
        if(isWarnEnabled()) this.api.log(SLF4JStatement.ofArguments(Level.WARN, format, null, arg));
    }

    @Override
    public void warn(String format, Object... arguments) {
        if(isWarnEnabled()) this.api.log(SLF4JStatement.ofArguments(Level.WARN, format, null, arguments));
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        if(isWarnEnabled()) this.api.log(SLF4JStatement.ofArguments(Level.WARN, format, null, arg1, arg2));
    }

    @Override
    public void warn(String msg, Throwable t) {
        if(isWarnEnabled()) this.api.log(SLF4JStatement.ofLiteral(Level.WARN, msg, t));
    }

    @Override
    public boolean isErrorEnabled() {
        return SLF4JStatement.fromSLF4JLevel(Level.ERROR) >= api.level();
    }

    @Override
    public void error(String msg) {
        if(isErrorEnabled()) this.api.log(SLF4JStatement.ofLiteral(Level.ERROR, msg, null));
    }

    @Override
    public void error(String format, Object arg) {
        if(isErrorEnabled()) this.api.log(SLF4JStatement.ofArguments(Level.ERROR, format, null, arg));
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        if(isErrorEnabled()) this.api.log(SLF4JStatement.ofArguments(Level.ERROR, format, null, arg1, arg2));
    }

    @Override
    public void error(String format, Object... arguments) {
        if(isErrorEnabled()) this.api.log(SLF4JStatement.ofArguments(Level.ERROR, format, null, arguments));
    }

    @Override
    public void error(String msg, Throwable t) {
        if(isErrorEnabled()) this.api.log(SLF4JStatement.ofLiteral(Level.ERROR, msg, t));
    }
}
