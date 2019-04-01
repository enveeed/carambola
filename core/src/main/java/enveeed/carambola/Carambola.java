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

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public final class Carambola {

    private static Carambola carambola = null;

    // ===

    private Carambola() {}

    // ===

    public static Carambola get() {
        if(carambola == null) {
            carambola = new Carambola();

            // attempt to load a configuration script
            carambola.attemptDSLConfiguration();
        }
        return carambola;
    }

    // ===

    /**
     * The expected file name for the configuration script, if existent.
     */
    private final static String CONFIGURATION_SCRIPT = "carambola.kts";

    // ===

    /**
     * Lock for configuration changes.
     */
    private final ReentrantLock configurationLock = new ReentrantLock();

    //

    /**
     * All currently active adapters.
     */
    private final Set<Adapter> adapters = new HashSet<>();

    /**
     * All currently active handlers.
     */
    private final Set<Handler> handlers = new HashSet<>();

    // === CONFIGURATION ===

    /**
     * Update carambola to use the given DSL configuration, replacing
     * the current one.
     * @param configuration the DSL configuration
     */
    public void apply(Configuration configuration) {

        configurationLock.lock();

        try {

            this.applyAdapters(configuration);
            this.applyHandlers(configuration);

        } finally {
            configurationLock.unlock();
        }
    }

    //

    private void applyAdapters(Configuration configuration) {

        Set<Adapter> adapters = configuration.getAdapters();

        for (Adapter adapter : adapters) {
            if(this.adapters.contains(adapter)) continue;

            adapter.initialize();

            this.adapters.add(adapter);
        }
    }

    private void applyHandlers(Configuration configuration) {

        Set<Handler> handlers = configuration.getHandlers();

        this.handlers.addAll(handlers);
    }

    // === LOG ===

    /**
     * Log the given statement.
     * @param statement the statement.
     */
    public void log(Statement statement) {
        for (Handler handler : this.handlers) handler.handle(statement);
    }

    // === DSL ===

    /**
     * Attempt to apply a {@link Configuration} from {@link #CONFIGURATION_SCRIPT}.
     */
    private void attemptDSLConfiguration() {

        // attempt to find the configuration script

        InputStream input = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(CONFIGURATION_SCRIPT);

        if(input == null) return; // TODO warn that there is no configuration script

        byte[] content;

        try {
            content = input.readAllBytes();
        } catch (Exception e) {
            // TODO this is an internal error and should be
            //  logged because it means that the file could not be read correctly
            e.printStackTrace();
            return;
        }

        // TODO document that configuration scripts are expected to be UTF-8
        String script = new String(content, StandardCharsets.UTF_8);

        // load the kotlin script engine

        ScriptEngine engine = new ScriptEngineManager(Thread.currentThread().getContextClassLoader())
                .getEngineByExtension("kts");

        // execute the script and get the configuration object

        Configuration configuration;

        try {
            configuration = (Configuration) engine.eval(script);
        } catch (Exception e) {
            // TODO warn that the configuration script was invalid.
            e.printStackTrace();
            return;
        }

        // apply the configuration

        this.apply(configuration);
    }

    // ===

    public Set<Adapter> getAdapters() {
        return Set.copyOf(this.adapters);
    }

    public Set<Handler> getHandlers() {
        return Set.copyOf(this.handlers);
    }
}
