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

package enveeed.carambola.script

import io.github.classgraph.ClassGraph
import org.jetbrains.kotlin.cli.common.repl.KotlinJsr223JvmScriptEngineFactoryBase
import org.jetbrains.kotlin.cli.common.repl.ScriptArgsWithTypes
import org.jetbrains.kotlin.script.jsr223.KotlinJsr223JvmLocalScriptEngine
import java.io.File
import javax.script.Bindings
import javax.script.ScriptContext
import javax.script.ScriptEngine
import kotlin.script.experimental.jvm.util.KotlinJars

const val TEMPLATE_CLASS: String = "org.jetbrains.kotlin.script.jsr223.KotlinStandardJsr223ScriptTemplate"

@Suppress("unused") // This is used in a service file in META-INF
class CarambolaScriptEngineFactory : KotlinJsr223JvmScriptEngineFactoryBase() {

    override fun getScriptEngine(): ScriptEngine {

        // assemble the classpath for the kotlin script
        val graph = ClassGraph()

        graph.whitelistPackages("enveeed.carambola")
        graph.whitelistClasses(TEMPLATE_CLASS)

        val classpath = mutableSetOf<File>()
        graph.scan().use {
            for(c in it.allClasses) {

                val file =
                        if(c.moduleRef != null) c.moduleRef.locationFile
                        else c.classpathElementFile

                if(file != null) classpath += file
                // skipping classes which do not have a file (e.g. system classes)
            }
        }

        // add the standard libraries
        classpath += KotlinJars.kotlinScriptStandardJars

        // create the engine
        return KotlinJsr223JvmLocalScriptEngine(
                this,
                classpath.toList(),
                TEMPLATE_CLASS,
                { ctx, types ->
                    ScriptArgsWithTypes(arrayOf(ctx.getBindings(ScriptContext.ENGINE_SCOPE)), types ?: emptyArray())
                },
                arrayOf(Bindings::class)
        )
    }
}