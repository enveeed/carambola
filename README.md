<img src="https://i.imgur.com/l8KkBKE.png" width="150" />

[![version][ref_badge_version]](https://bintray.com/enveeed/carambola)
[![build][ref_badge_build]](https://travis-ci.com/enveeed/carambola/)


[ref_badge_version]: https://img.shields.io/badge/dynamic/json.svg?color=%23AFB42B&label=latest&query=name&style=flat-square&url=https%3A%2F%2Fapi.bintray.com%2Fpackages%2Fenveeed%2Fcarambola%2Fcarambola-core%2Fversions%2F_latest
[ref_badge_build]: https://img.shields.io/travis/com/enveeed/carambola.svg?style=flat-square

# carambola

**carambola** is a logging aggregator for multiple different logging APIs and facades, 
which allows you to configure and handle logging in a central place. It provides a redirecting implementation for
all supported APIs. This is especially useful if your project uses a lot of libraries 
which use different or varying versions of logging APIs.

## Supported APIs

carambola provides implementations for the following logging APIs or facades:

| API | Module | Notes |
| --- | --- | --- |
| [Flogger][ref_flogger] | `carambola-flogger` |   |
| [SLF4J][ref_slf4j] | `carambola-slf4j` | SLF4J versions `1.8+`, currently always active when on the classpath. |
| [JUL][ref_jul] | `carambola-jul` |  |

`carambola-core` is always required.

[ref_flogger]: https://github.com/google/flogger
[ref_slf4j]: https://slf4j.org
[ref_jul]: https://docs.oracle.com/en/java/javase/11/docs/api/java.logging/java/util/logging/LogManager.html

## Gradle

carambola artifacts are hosted on [Bintray JCenter](https://bintray.com/bintray/jcenter), to query this repository
add the following to your `repository` and `dependencies` block, respectively:

> **NOTE:** You should always replace the `+` (latest) version with a manually configured one, otherwise
> it may be possible for incompatibilities and unexpected changes to happen.

**Groovy DSL**
```Groovy
repositories {
    jcenter()
}

// ...

dependencies {
    implementation group: 'enveeed.carambola', name: 'carambola-core', version: '+'
}
```

**Kotlin DSL**
```Kotlin
repositories {
    jcenter()
}

// ...

dependencies {
    implementation(group = "enveeed.carambola", name = "carambola-core",version = "+")
}

```

---

For the different implementations also add dependencies for 
the module (e.g.: `carambola-flogger`) in the place of `carambola-core`.

## Principle

Carambola works with **Adapters** and **Handlers**.

**Adapters** deliver log statements
from different logging APIs to carambola and make sure that they are as uniform as possible.

**Handlers** handle the log statements which arrive at carambola in any customizable way.
Handlers also include filtering, which enables you to for example only log severe errors with
one handler, but everything with another, and so on. 

## Usage

### Configuration

Configuration is done via a *configuration script* written in a **Kotlin DSL**. 
This allows you to include both declarative elements and functional ones in the configuration
and enables you to use Java and Kotlin both to full capacity in the configuration, for example
when adding custom handlers or adapters.

The configuration script must be called `carambola.kts` and must be located
at the root of the classpath. Carambola also works without one, in this case you need to manually
configure it from your application.

---

The script has the following **implicit imports**, 
that means you do not have to add those imports for the script to work:

- `enveeed.carambola.*`
- `enveeed.carambola.dsl.*`
- `enveeed.carambola.handlers.*`
- `enveeed.carambola.filters.*`
- `java.util.logging.*`

Note that due to performance reasons, 
**only the carambola classes and Kotlin standard libraries are on the classpath** for the script. 

---

**Example `carambola.kts`**
```Kotlin

carambola {

    adapters {

        // useFlogger() // for carambola-flogger
        // useSLF4J() // for carambola-slf4j
    }

    handlers {

        handler({
            System.out.println(this.content)
        }) {
            filter {
                this.level >= 100
            }
        }
    }

    level(50)

}
```

**Syntax**

- `carambola {}` - Main configuration block, includes all configuration.
  - `adapters {}` - Adapters configuration block, all adapters are added here.
    - `adapter(adapter: Adapter)` - add an adapter
    - `use...()` - extension functions provided by carambola modules to make adding their adapters easier.
  - `handlers {}` - Handlers configuration block, all handlers are added here.
    - `handler(executor: HandlerExecutor) {}` - Handler configuration block / function, adds a handler.
      - `filter(filter: Filter)` - add a filter to the handler
      - `filter...()` - various filter methods to add standard filters
  - `level(level: Int)` - global level filter, to limit all level output before it reaches the handlers. 
  (Useful for APIs which rely on this for speed benefits, e.g. Flogger)

### Setup

To use carambola in your project, you need to call `Carambola.init(...)` at the start
of the program. Any logging statements before that will not be able to be handled by carambola
or will be no-op. After carambola was initialized, every adapted logging APIs output will happen
via carambola.

**Example**

```Java

import enveeed.carambola.*;

public class Main {
    
    public static void main(String[] args) {
        
        Carambola.init(); // use the simplest default initializer
        
        // ...
    }
}

```

## License

This software is licensed under the **[GNU General Public License v3.0](https://www.gnu.org/licenses/)**.