![version](https://img.shields.io/badge/version-2019.0.0--beta-blue.svg?style=flat-square)
![java-version](https://img.shields.io/badge/java-11-yellowgreen.svg?style=flat-square)

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


[ref_flogger]: https://github.com/google/flogger
[ref_slf4j]: https://slf4j.org
[ref_jul]: https://docs.oracle.com/en/java/javase/11/docs/api/java.logging/java/util/logging/LogManager.html

