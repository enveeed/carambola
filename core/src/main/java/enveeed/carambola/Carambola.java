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
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/**
 * Carambola main class.
 * @see #init()
 * @see #init(Path)
 * @see #init(String)
 * @see #init(Configuration)
 * @see #get()
 */
public final class Carambola {
    private Carambola() {}

    // ===

    private static CarambolaApi api = null;

    // ===

    /**
     * Get the global carambola API instance.
     * If carambola was not initialized yet, a no-op instance
     * will be returned instead. This method should be used by adapters to
     * obtain the API instance.
     * @return the carambola API
     */
    public static CarambolaApi get() {
        if(api == null) return CarambolaApi.getNoOp();
        else return api;
    }

    // ===

    /**
     * The default script file name to look for at the classpath root.
     */
    private static final String DEFAULT_SCRIPT_FILE_NAME = "carambola.kts";

    // ===

    /**
     * Initialize carambola using the given configuration.
     * @param configuration the configuration
     * @throws CarambolaInitException if carambola could not be correctly initialized
     */
    public static void init(Configuration configuration) throws CarambolaInitException {
        Objects.requireNonNull(configuration);
        ensureNotInitialized();

        //

        api = CarambolaApi.getConfigured(configuration);
    }

    /**
     * Initialize carambola using the given configuration script.
     * @param configurationScript the configuration script
     * @throws CarambolaInitException if carambola could not be correctly initialized
     */
    public static void init(String configurationScript) throws CarambolaInitException {
        Objects.requireNonNull(configurationScript);
        ensureNotInitialized();

        ScriptEngine engine = new ScriptEngineManager(Thread.currentThread().getContextClassLoader())
                .getEngineByExtension("kts");

        if(engine == null) throw new CarambolaInitException("Kotlin script engine was unavailable." +
                "Are all carambola dependencies and service definitions on the classpath?");

        Object result;

        try {
            result = engine.eval(configurationScript);
        } catch (ScriptException e) {
            throw new CarambolaInitException("Invalid configuration script!", e);
        }

        if(!(result instanceof Configuration))
            throw new CarambolaInitException("Configuration script returned invalid configuration, is it valid? Type was " +
                    (result == null ? "(result was null)" : result.getClass().getName()));

        Configuration configuration = (Configuration) result;

        //

        init(configuration);
    }

    /**
     * Initialize carambola using the configuration script at the given path.
     * @param configurationScriptFile the configuration script file path
     * @throws CarambolaInitException if carambola could not be correctly initialized
     */
    public static void init(Path configurationScriptFile) throws CarambolaInitException {
        Objects.requireNonNull(configurationScriptFile);
        ensureNotInitialized();

        byte[] data;

        try {
            data = Files.readAllBytes(configurationScriptFile);
        } catch (IOException e) {
            throw new CarambolaInitException("Failed to read configuration script file!", e);
        }

        String script = new String(data, StandardCharsets.UTF_8);

        //

        init(script);
    }

    //

    /**
     * Initialize carambola using the default configuration script at the root
     * of the classpath.
     * @throws CarambolaInitException if carambola could not be correctly initialized
     */
    public static void init() throws CarambolaInitException {
        ensureNotInitialized();

        InputStream stream = Carambola.class.getResourceAsStream(DEFAULT_SCRIPT_FILE_NAME);

        if(stream == null)
            throw new CarambolaInitException("Default carambola configuration script not found: " + DEFAULT_SCRIPT_FILE_NAME);

        byte[] data;

        try {
            data = stream.readAllBytes();
        } catch (IOException e) {
            throw new CarambolaInitException("Failed to read configuration script file!", e);
        }

        String script = new String(data, StandardCharsets.UTF_8);

        //

        init(script);
    }

    //

    private static void ensureNotInitialized() throws CarambolaInitException {
        if(api != null) throw new CarambolaInitException("carambola was already initialized!");
    }

}
