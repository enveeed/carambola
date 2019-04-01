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

package enveeed.carambola.dsl

import enveeed.carambola.*

// === HANDLERS ===

/**
 * DSL block to collect [Handler]s.
 * @see Handler
 * @see HandlerBlock
 */
@CarambolaDslMarker
class HandlersBlock {

    private var handlers: Set<Handler> = mutableSetOf()

    //

    fun handler(executor: HandlerExecutor, setup: HandlerBlock.() -> Unit = {}) {
        val block = HandlerBlock(executor)
        block.setup()
        handlers = handlers + block.build()
    }

    fun handler(executor: Statement.() -> Unit, setup: HandlerBlock.() -> Unit = {}) {
        handler(HandlerExecutor(executor), setup)
    }

    fun handler(handler: Handler) {
        handlers = handlers + handler
    }

    // ===

    fun build(): Set<Handler> {
        return this.handlers
    }
}

//

/**
 * DSL block to setup a new [Handler].
 * @see Handler
 * @see HandlersBlock
 */
@CarambolaDslMarker
class HandlerBlock(executor: HandlerExecutor) {

    private val executor: HandlerExecutor = executor
    private var filters: Set<Filter> = mutableSetOf()

    //

    fun filter(filter: Filter) {
        this.filters = filters + filter
    }

    fun filter(filter: Statement.() -> Boolean) {
        filter(Filter(filter))
    }

    // ===

    fun build(): Handler {
        return Handler(executor, filters)
    }
}

// === ADAPTERS ===

/**
 * DSL block to collect [Adapter]s.
 * @see Adapter
 */
@CarambolaDslMarker
class AdaptersBlock {

    private var adapters: Set<Adapter> = mutableSetOf()

    //

    /**
     * Add an [Adapter].
     */
    fun adapter(adapter: Adapter) {
        adapters = adapters + adapter
    }

    // ===

    fun build(): Set<Adapter> {
        return this.adapters
    }
}

// === CONFIGURATION / CARAMBOLA ===

@CarambolaDslMarker
class ConfigurationBlock {

    private var adapters: Set<Adapter> = mutableSetOf()
    private var handlers: Set<Handler> = mutableSetOf()

    //

    fun adapters(setup: AdaptersBlock.() -> Unit = {}) {
        val block = AdaptersBlock()
        block.setup()
        adapters = adapters + block.build()
    }

    fun handlers(setup: HandlersBlock.() -> Unit = {}) {
        val block = HandlersBlock()
        block.setup()
        handlers = handlers + block.build()
    }

    // ===

    fun build(): Configuration {
        return Configuration(adapters, handlers)
    }

    // ===

    @Suppress("UNUSED_PARAMETER")
    @Deprecated(level = DeprecationLevel.ERROR, message = "'carambola' blocks can't be nested.")
    fun carambola(param: () -> Unit = {}) {}
}

// ===

@CarambolaDslMarker
fun carambola(setup: ConfigurationBlock.() -> Unit): Configuration {
    val block = ConfigurationBlock()
    block.setup()
    return block.build()
}