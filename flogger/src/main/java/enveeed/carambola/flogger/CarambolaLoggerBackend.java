package enveeed.carambola.flogger;

import com.google.common.flogger.backend.LogData;
import com.google.common.flogger.backend.LoggerBackend;
import enveeed.carambola.Carambola;

import java.util.logging.Level;

public final class CarambolaLoggerBackend extends LoggerBackend {

    private final Carambola carambola = Carambola.get();

    private final String name;

    // ===

    CarambolaLoggerBackend(String name) {
        this.name = name;
    }

    // ===

    @Override
    public String getLoggerName() {
        return this.name;
    }

    @Override
    public boolean isLoggable(Level lvl) {
        return true;
    }

    @Override
    public void log(LogData data) {
        System.out.println("CARAMBOLAAAA "+data.toString());
    }

    @Override
    public void handleError(RuntimeException error, LogData badData) {

    }

}
