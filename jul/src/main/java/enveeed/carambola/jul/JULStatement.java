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

import enveeed.carambola.Site;
import enveeed.carambola.Statement;

import java.time.Instant;
import java.util.Optional;
import java.util.logging.LogRecord;

@Deprecated
public final class JULStatement implements Statement {

    private final LogRecord record;

    // ===

    JULStatement(LogRecord record) {
        this.record = record;
    }

    // ===

    @Override
    public int getLevel() {
        return this.record.getLevel().intValue();
    }

    @Override
    public Instant getTimestamp() {
        return this.record.getInstant();
    }

    @Override
    public String getContent() {

        String content;

        if(this.record.getParameters() != null) {
            content = String.format(this.record.getMessage(), this.record.getParameters());
        } else {
            if(this.record.getMessage() != null)
                content = this.record.getMessage();
            else
                content = "";
        }

        return content;
    }

    //

    @Override
    public Optional<Throwable> getCause() {
        return Optional.ofNullable(this.record.getThrown());
    }

    @Override
    public Optional<Site> getSite() {
        // TODO this should be optional if info is missing.
        return Optional.of(new Site(this.record.getSourceClassName(), this.record.getSourceMethodName(), -1));
    }
}
