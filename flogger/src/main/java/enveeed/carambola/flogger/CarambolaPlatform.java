package enveeed.carambola.flogger;

import com.google.common.flogger.backend.LoggerBackend;
import com.google.common.flogger.backend.Platform;
import com.google.common.flogger.backend.system.StackBasedCallerFinder;

public final class CarambolaPlatform extends Platform {

    @Override
    protected LogCallerFinder getCallerFinderImpl() {
        return StackBasedCallerFinder.getInstance();
    }

    @Override
    protected LoggerBackend getBackendImpl(String className) {
        return new CarambolaLoggerBackend(className);
    }

    @Override
    protected String getConfigInfoImpl() {
        return "platform: " + "CarambolaPlatform" + "\n";
    }
}
