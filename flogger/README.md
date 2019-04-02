# carambola-flogger

**`carambola-flogger`** provides a simple
[Backend](./src/main/java/enveeed/carambola/flogger/CarambolaBackend.java) for the
[Google Flogger](https://github.com/google/flogger) API.

# Configuration

With `carambola-flogger` on the classpath, add the following to your `adapters` block in `carambola.kts`:

```Kotlin

import enveeed.carambola.flogger.dsl.*

adapters {

    // ... any other adapters
    
    useFlogger()
    
}
```