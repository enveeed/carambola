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

import java.util.Set;

/**
 * A handler for {@link Statement Statements}.
 * <p/>
 * Consists of an {@link HandlerExecutor} which does the actual handling of the
 * statements, and zero or more {@link Filter Filters}, which all need to match
 * for the statement to actually reach the executor.
 */
public final class Handler {

    private final HandlerExecutor executor;
    private final Set<Filter> filters;

    // ===

    public Handler(HandlerExecutor executor, Set<Filter> filters) {
        this.executor = executor;
        this.filters = filters;
    }

    // ===

    void handle(Statement statement) {

        // test the statement with all filters
        for (Filter filter : this.filters) if (!filter.test(statement)) return;

        // passed all filters, handle it
        this.executor.handle(statement);
    }
}
