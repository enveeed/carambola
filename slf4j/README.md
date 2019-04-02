# carambola-slf4j

**`carambola-slf4j`** provides an implementation of the SLF4J API for redirection to Carambola.

> **NOTE:** This currently only supports SLF4J API versions `1.8+`

# Configuration

With `carambola-slf4j` on the classpath, add the following to your `adapters` block in `carambola.kts`:

```Kotlin

import enveeed.carambola.slf4j.dsl.*

adapters {

    // ... any other adapters
    
    useSLF4J()
    
}
```

> **NOTE:** Currently the above is not necessary because due to 
> the technique the SLF4J API uses to load the implementation it is currently always
> active when on the classpath, however, for future compatibility it is useful to add. 