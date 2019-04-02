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

import enveeed.carambola.dsl.Configuration;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * The carambola API.
 */
public abstract class CarambolaApi {
    private CarambolaApi() {}

    private static final NoOpApi NO_OP_API = new NoOpApi();

    // ===

    static CarambolaApi getNoOp() {
        return NO_OP_API;
    }

    static CarambolaApi getConfigured(Configuration configuration) {
        Objects.requireNonNull(configuration);
        return new ConfiguredApi(configuration.getAdapters(), configuration.getHandlers(), configuration.getLevel());
    }

    // ===

    /**
     * Get a set of all active adapters.
     * @return adapters set
     */
    public abstract Set<Adapter> adapters();

    /**
     * Get a set of all active handlers.
     * @return handlers set.
     */
    public abstract Set<Handler> handlers();

    /**
     * Get the current global minimum level.
     * @return the minimum level.
     */
    public abstract int level();

    //

    /**
     * Log the given statement with all active handlers.
     * @param statement the statement
     */
    public abstract void log(Statement statement);

    // === NO-OP ===

    private static class NoOpApi extends CarambolaApi {
        private NoOpApi() {}

        @Override
        public Set<Adapter> adapters() {
            return Collections.emptySet();
        }

        @Override
        public Set<Handler> handlers() {
            return Collections.emptySet();
        }

        @Override
        public int level() {
            // this means pre-filtering APIs already drop everything beforehand
            return Integer.MAX_VALUE;
        }

        //

        @Override
        public void log(Statement statement) {
            // NO-OP
        }
    }

    // === CONFIGURED ===

    private static class ConfiguredApi extends CarambolaApi {

        /**
         * All currently active adapters.
         */
        private final Set<Adapter> adapters;

        /**
         * All currently active handlers.
         */
        private final Set<Handler> handlers;

        /**
         * The global minimum level for pre-filtering
         */
        private int level;

        // ===

        private ConfiguredApi(Set<Adapter> adapters, Set<Handler> handlers, int level) {
            this.adapters = adapters;
            this.handlers = handlers;
            this.level = level;
        }

        // ===

        @Override
        public Set<Adapter> adapters() {
            return Collections.unmodifiableSet(this.adapters);
        }

        @Override
        public Set<Handler> handlers() {
            return Collections.unmodifiableSet(this.handlers);
        }

        @Override
        public int level() {
            return this.level;
        }

        //

        @Override
        public void log(Statement statement) {
            for (Handler handler : this.handlers) handler.handle(statement);
        }
    }
}
