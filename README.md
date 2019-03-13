![version](https://img.shields.io/badge/dynamic/json.svg?color=%23AFB42B&label=latest&query=name&style=flat-square&url=https%3A%2F%2Fapi.bintray.com%2Fpackages%2Fenveeed%2Fcarambola%2Fcarambola-core%2Fversions%2F_latest&link=https://bintray.com/enveeed/carambola)

> **NOTE:** This is not intended to be used in projects as of right now, since it's basically just a stub.
> If you want to use it in your own projects, please consider waiting for a complete (non`-beta`) release.

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
| [SLF4J][ref_slf4j] | `carambola-slf4j` | Includes support for both pre-`1.8` APIs and `1.8+` APIs |
| [JUL][ref_jul] | `carambola-jul` | Does not automatically enable, needs to be set via system property (see docs)|

`carambola-core` is always required.

[ref_flogger]: https://github.com/google/flogger
[ref_slf4j]: https://slf4j.org
[ref_jul]: https://docs.oracle.com/en/java/javase/11/docs/api/java.logging/java/util/logging/LogManager.html

## Gradle

carambola artifacts are hosted on [Bintray](https://bintray.com/enveeed/carambola), to query this repository
add the following to your `repository` and `dependencies` block, respectively:

> **NOTE:** You should always replace the `+` (latest) version with a manually configured one, otherwise
> it may be possible for incompatibilities and unexpected changes to happen.

**Groovy DSL**
```Groovy
repositories {
    maven {
        url 'https://dl.bintray.com/enveeed/carambola'
    }
}

// ...

dependencies {
    implementation group: 'enveeed.carambola', name: 'carambola-core', version: '+'
}
```

**Kotlin DSL**
```Kotlin
repositories {
    maven("https://dl.bintray.com/enveeed/carambola")
}

// ...

dependencies {
    implementation(group = "enveeed.carambola", name = "carambola-core",version = "+")
}

```

---

For the different implementations also add dependencies for 
the module (e.g.: `carambola-flogger`) in the place of `carambola-core`.

## License

This software is licensed under the **[GNU General Public License v3.0](https://www.gnu.org/licenses/)**.