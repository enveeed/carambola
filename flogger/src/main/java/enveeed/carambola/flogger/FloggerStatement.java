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

import com.google.common.flogger.LogContext;
import com.google.common.flogger.LogSite;
import com.google.common.flogger.backend.LogData;
import enveeed.carambola.Site;
import enveeed.carambola.Statement;

import java.time.Instant;
import java.util.Optional;

public final class FloggerStatement implements Statement {

    private final LogData data;

    // ===

    private FloggerStatement(LogData data) {
        this.data = data;
    }

    // ===

    public static FloggerStatement of(LogData data) {
        return new FloggerStatement(data);
    }

    // ===

    @Override
    public Instant getTimestamp() {
        return Instant.ofEpochMilli((long) (data.getTimestampNanos() / 1e6));
    }

    @Override
    public String getContent() {

        String content;

        if(this.data.getTemplateContext() == null) {
            content = String.format("%s", this.data.getLiteralArgument());
        }
        else {
            content = String.format(this.data.getTemplateContext().getMessage(), this.data.getArguments());
        }

        return content;
    }

    // ===

    @Override
    public int getLevel() {
        return this.data.getLevel().intValue();
    }

    //

    @Override
    public Optional<Throwable> getCause() {
        Throwable cause = this.data.getMetadata().findValue(LogContext.Key.LOG_CAUSE);
        return Optional.ofNullable(cause);
    }

    @Override
    public Optional<Site> getSite() {
        LogSite site = this.data.getLogSite();
        if(site == LogSite.INVALID) return Optional.empty();
        else return Optional.of(new Site(site.getClassName(), site.getMethodName(), site.getLineNumber()));
    }
}
